package project.penadidik.cleanarchitecture.data.remote.api

import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.data.remote.response.SearchUserResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users/")
    fun getUsers(): Single<List<UserEntity>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<UserEntity>

    @POST("users/signin")
    fun signin(@Path("username") userName: String, @Path("password") password: String): Completable

    @GET("search/users")
    fun searchUsers(@Query("q") query: String, @Query("page") page: Int, @Query("sort") sort: String): Single<SearchUserResponse>
}