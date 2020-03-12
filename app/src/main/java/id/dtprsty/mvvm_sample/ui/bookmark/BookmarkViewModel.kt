package id.dtprsty.mvvm_sample.ui.bookmark

import androidx.lifecycle.ViewModel
import id.dtprsty.mvvm_sample.data.CourseEntity
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmark(): MutableList<CourseEntity> = academyRepository.getBookmarkedCourses()
}