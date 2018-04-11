package cz.helu.helublocks.extension

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun Throwable.isNetworkUnavailable(): Boolean = this is SocketTimeoutException || this is ConnectException || this is UnknownHostException || (this is HttpException && this.code() == 504)


