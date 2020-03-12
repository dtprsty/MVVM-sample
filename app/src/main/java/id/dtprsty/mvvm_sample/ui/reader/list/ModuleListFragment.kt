package id.dtprsty.mvvm_sample.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.ModuleEntity
import id.dtprsty.mvvm_sample.ui.reader.CourseReaderCallback
import id.dtprsty.mvvm_sample.ui.reader.CourseReaderViewModel
import id.dtprsty.mvvm_sample.utils.DataDummy
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_list.*

/**
 * A simple [Fragment] subclass.
 */
class ModuleListFragment : Fragment(), ModuleListAdapter.ModuleListAdapterListener {

    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    companion object{
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        initRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderCallback
    }

    private fun initRecyclerView(){
        progressBar.visibility = View.GONE
        val modules = viewModel.getModules()
        val adapter = ModuleListAdapter(this, modules)
        with(rvModule){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rvModule.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onClick(position: Int, model: ModuleEntity) {
        courseReaderCallback.moveTo(position, model.moduleId)
        viewModel.setSelectedModule(model.moduleId)
    }
}
