package id.dtprsty.mvvm_sample.data.source.remote.response

import id.dtprsty.mvvm_sample.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    companion object{
        @Volatile
        private var remoteDataSource: RemoteDataSource? = null

        fun geInstance(helper: JsonHelper): RemoteDataSource{
            return remoteDataSource ?: synchronized(this){
                remoteDataSource ?: RemoteDataSource(helper)
            }
        }
    }

    fun getAllCourses(): MutableList<CourseResponse> = jsonHelper.loadCourses()

    fun getModules(courseId: String): MutableList<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)
}