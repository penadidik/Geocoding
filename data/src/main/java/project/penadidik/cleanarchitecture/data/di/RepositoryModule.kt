package project.penadidik.cleanarchitecture.data.di

import android.content.Context
import androidx.room.Room
import project.penadidik.cleanarchitecture.data.Constants
import project.penadidik.cleanarchitecture.data.local.db.AppDatabase
import project.penadidik.cleanarchitecture.data.local.pref.AppPrefs
import project.penadidik.cleanarchitecture.data.local.pref.PrefHelper
import project.penadidik.cleanarchitecture.data.repository.UserRepositoryImpl
import project.penadidik.cleanarchitecture.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @project.penadidik.cleanarchitecture.data.di.DatabaseInfo
    fun providerDatabaseName(): String {
        return Constants.DATABASE_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@project.penadidik.cleanarchitecture.data.di.DatabaseInfo dbName: String, @ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
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
    fun providerUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }
}
