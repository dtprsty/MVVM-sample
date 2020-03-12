package id.dtprsty.mvvm_sample.di

import android.content.Context
import id.dtprsty.mvvm_sample.data.source.AcademyRepository
import id.dtprsty.mvvm_sample.data.source.remote.response.RemoteDataSource
import id.dtprsty.mvvm_sample.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository{
        val remoteDataSource = RemoteDataSource.geInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}