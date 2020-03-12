package id.dtprsty.mvvm_sample.ui.academy

import androidx.lifecycle.ViewModel
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): MutableList<CourseEntity> = academyRepository.getAllCourses()
}