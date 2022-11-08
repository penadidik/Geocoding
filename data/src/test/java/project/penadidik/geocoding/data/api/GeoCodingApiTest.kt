package project.penadidik.geocoding.data.api

import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import project.penadidik.geocoding.data.Constants
import project.penadidik.geocoding.data.model.DirectEntity
import project.penadidik.geocoding.data.remote.api.GeoCodingApi
import project.penadidik.geocoding.data.remote.api.UserApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class GeoCodingApiTest {

    private lateinit var geoCodingApi: GeoCodingApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        geoCodingApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GeoCodingApi::class.java)
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
        val testObserver = TestObserver<List<DirectEntity>>()
        val query = "Australia"
        val limit = 5
        val appid = Constants.Authentication.APPID

        val path = "/geo/1.0/direct?appid=$appid&q=$query&limit=$limit"
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("search.json")

        mockWebServer.enqueue(mockResponse)

        geoCodingApi.searchDirect(query, limit, appid).toObservable().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val request = mockWebServer.takeRequest()
        assertEquals(request.path, path)

        testObserver.assertValue { response ->
            response.isNotEmpty()
        }
    }

}