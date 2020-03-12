package id.dtprsty.mvvm_sample.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}