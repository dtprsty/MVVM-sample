package id.dtprsty.mvvm_sample.ui.reader

import android.util.Log
import androidx.lifecycle.ViewModel
import id.dtprsty.mvvm_sample.data.ContentEntity
import id.dtprsty.mvvm_sample.data.ModuleEntity
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String){
        this.moduleId = moduleId
    }

    fun getModules(): MutableList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)
}