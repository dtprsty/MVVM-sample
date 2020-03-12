package id.dtprsty.mvvm_sample.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.di.Injection
import id.dtprsty.mvvm_sample.ui.academy.AcademyViewModel
import id.dtprsty.mvvm_sample.ui.bookmark.BookmarkViewModel
import id.dtprsty.mvvm_sample.ui.detail.DetailViewModel
import id.dtprsty.mvvm_sample.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val academyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                AcademyViewModel(academyRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(academyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(academyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                CourseReaderViewModel(academyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class : ${modelClass.name}")
        }
    }
}