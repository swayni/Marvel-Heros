package sw.swayni.marvelheros.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import sw.swayni.marvelheros.BuildConfig
import sw.swayni.marvelheros.data.Api
import sw.swayni.marvelheros.data.database.AppDatabase
import sw.swayni.marvelheros.data.local.ILocalDataSource
import sw.swayni.marvelheros.data.local.LocalDataSource
import sw.swayni.marvelheros.data.remote.IRemoteDataSource
import sw.swayni.marvelheros.data.remote.RemoteDataSource
import sw.swayni.marvelheros.data.repository.IRepository
import sw.swayni.marvelheros.data.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext app: Context): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, BuildConfig.DATABASE_NAME).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideLocalDataSource(appDatabase: AppDatabase): ILocalDataSource = LocalDataSource(appDatabase)

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): IRemoteDataSource = RemoteDataSource(api)

    @Singleton
    @Provides
    fun provideRepository(localDataSource: ILocalDataSource, remoteDataSource: RemoteDataSource): IRepository = Repository(localDataSource, remoteDataSource)

}