package cz.helu.helublocks.extension

import retrofit2.Response

// mapResource response to another response using body mapResource function
fun <T, S> Response<T>.map(mapFunction: (T?) -> S?) = if (isSuccessful) Response.success(mapFunction(body()), raw()) else Response.error(errorBody()!!, raw())
