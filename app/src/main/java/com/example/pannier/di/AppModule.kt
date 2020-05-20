package com.example.pannier.di

import android.content.Context
import com.example.pannier.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule() {
    @Provides
    @Singleton
    fun provideApplication(app : App): Context = app
}