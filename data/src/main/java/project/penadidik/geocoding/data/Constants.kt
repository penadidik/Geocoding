package project.penadidik.geocoding.data

object Constants {

    const val DATABASE_NAME = "GeoCoding.db"

    object HttpClient {
        const val CONNECT_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val CONNECTION_TIME_OUT_MLS = CONNECT_TIMEOUT * 1000L
    }

    object Authentication {
        const val MAX_RETRY = 1
        const val APPID = "8576d8ae4739aa6e3d7129239755bbb5"
    }
}