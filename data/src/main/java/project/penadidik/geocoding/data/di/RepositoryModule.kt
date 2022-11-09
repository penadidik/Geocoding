package project.penadidik.geocoding.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import project.penadidik.geocoding.data.Constants
import project.penadidik.geocoding.data.local.db.AppDatabase
import project.penadidik.geocoding.data.local.pref.AppPrefs
import project.penadidik.geocoding.data.local.pref.PrefHelper
import project.penadidik.geocoding.data.repository.GeoCodingRepositoryImpl
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @DatabaseInfo
    fun providerDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, @ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerAppPrefs(@ApplicationContext context: Context): AppPrefs {
        return AppPrefs(context, Gson())
    }

    @Provides
    @Singleton
    fun providerGeoCodingRepository(repository: GeoCodingRepositoryImpl): GeoCodingRepository {
        return repository
    }
}
