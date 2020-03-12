package id.dtprsty.mvvm_sample.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.ui.detail.DetailCourseActivity
import id.dtprsty.mvvm_sample.utils.DataDummy
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class AcademyFragment : Fragment(), AcademyAdapter.AcademyAdapter1Callback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]
            val courses = viewModel.getCourses()
            val academyAdapter = AcademyAdapter(this, courses)

            with(rvAcademy){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

    override fun onClick(model: CourseEntity) {
        context?.startActivity<DetailCourseActivity>(DetailCourseActivity.EXTRA_COURSE to model.courseId)
    }
}
