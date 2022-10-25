package project.penadidik.cleanarchitecture.data.api

import project.penadidik.cleanarchitecture.data.ObserverTestUtils.getJson
import project.penadidik.cleanarchitecture.data.remote.api.UserApi
import project.penadidik.cleanarchitecture.data.remote.response.SearchUserResponse
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class UserApiTest {

    private lateinit var userApi: UserApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        userApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun testMockResponse() {
        val mockedResponse = MockResponse()

        mockedResponse.setResponseCode(200)
        mockedResponse.setBody("{}")

        mockWebServer.enqueue(mockedResponse)
    }

    @Test
    fun testSearchResponseOk() {
        val testObserver = TestObserver<SearchUserResponse>()
        val query = "query"
        val page = 1
        val sort = "ASC"

        val path = "/search/users?q=$query&page=$page&sort=$sort"
        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJson("search.json"))

        // Enqueue request
        mockWebServer.enqueue(mockResponse)

        // Call API
        userApi.searchUsers(query, page, sort).toObservable().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // then no error
        testObserver.assertNoErrors()
        // check values
        testObserver.assertValueCount(1)

        // get request to test
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, path)

        // test success value by search.json
        testObserver.assertValue { responseUser ->
            responseUser.total == 41 &&
                    responseUser.items.get(0).id == 63478084
        }
    }

    @Test
    fun testSearchResponseError() {
    }
}