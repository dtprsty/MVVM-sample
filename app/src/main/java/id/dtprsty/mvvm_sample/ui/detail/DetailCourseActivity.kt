package id.dtprsty.mvvm_sample.ui.detail

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dtprsty.mvvm_sample.R
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.ui.academy.AcademyViewModel
import id.dtprsty.mvvm_sample.ui.reader.CourseReaderActivity
import id.dtprsty.mvvm_sample.utils.DataDummy
import id.dtprsty.mvvm_sample.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*
import org.jetbrains.anko.startActivity

class DetailCourseActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        var adapter = DetailCourseAdapter()
        val extras = intent.extras
        if(extras != null){
            val courseId = extras.getString(EXTRA_COURSE)
            if(courseId != null){
                val modules = viewModel.getModules(courseId)
                adapter = DetailCourseAdapter(modules)
                populateCourse(viewModel.getCourse(courseId))
            }
        }

        with(rvModule){
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rvModule.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(model: CourseEntity?){
        tvTitle.text = model?.title
        tvDesc.text = model?.description
        tvDate.text = resources.getString(R.string.deadline_date, model?.deadline)

        Glide.with(this)
            .load(model?.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(ivPoster)

        btnStart.setOnClickListener {
            startActivity<CourseReaderActivity>(CourseReaderActivity.EXTRA_COURSE_ID to model?.courseId)
            Log.d("detail", model?.courseId)
        }
    }

}
