package id.dtprsty.mvvm_sample.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.ContentEntity
import id.dtprsty.mvvm_sample.data.ModuleEntity
import id.dtprsty.mvvm_sample.ui.academy.AcademyViewModel
import id.dtprsty.mvvm_sample.ui.reader.CourseReaderViewModel
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

/**
 * A simple [Fragment] subclass.
 */
class ModuleContentFragment : Fragment() {

    companion object{
        val TAG = ModuleContentFragment::class.java

        fun newInstance(): ModuleContentFragment{
            return ModuleContentFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(moduleEntity: ModuleEntity?){
        webView.loadData(moduleEntity?.contentEntity?.content, "text/html", "UTF-8")
    }

}
