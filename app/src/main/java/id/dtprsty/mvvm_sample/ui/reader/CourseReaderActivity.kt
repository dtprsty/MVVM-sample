package id.dtprsty.mvvm_sample.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.ui.academy.AcademyViewModel
import id.dtprsty.mvvm_sample.ui.reader.content.ModuleContentFragment
import id.dtprsty.mvvm_sample.ui.reader.list.ModuleListFragment
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {
    companion object  {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[CourseReaderViewModel::class.java]
        val extras = intent.extras
        if(extras != null){
            val courseId = extras.getString(EXTRA_COURSE_ID)
            if(courseId != null) {
                viewModel.setSelectedCourse(extras.getString(EXTRA_COURSE_ID) ?: "")
                initFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frameContainer,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount <= 1){
            finish()
        }else{
            super.onBackPressed()
        }
    }

    private fun initFragment(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if(fragment == null){
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frameContainer, fragment, ModuleListFragment.TAG)
                .addToBackStack(null)
        }
        fragmentTransaction.commit()
    }


}
