package id.dtprsty.mvvm_sample.ui.detail

import androidx.lifecycle.ViewModel
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.data.ModuleEntity
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.utils.DataDummy

class DetailViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourse(courseId: String): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(courseId: String): MutableList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}