package cz.helu.helublocks.extension

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.alfonz.rest.ResponseHandler
import retrofit2.Response

// TT Schedulers
fun <T> Single<T>.applySchedulers(): Single<T> {
	return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}


fun <T> Maybe<T>.applySchedulers(): Maybe<T> {
	return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}


fun <T> Flowable<T>.applySchedulers(): Flowable<T> {
	return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread(), true)
}


fun <T> Observable<T>.applySchedulers(): Observable<T> {
	return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread(), true)
}


fun Completable.applySchedulers(): Completable {
	return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}


// TT HTTP Error catching
fun <T : Response<*>> Single<T>.catchHttpError(responseHandler: ResponseHandler): Single<T> = flatMap {
	if (responseHandler.isSuccess(it)) {
		Single.just(it)
	} else {
		Single.error<T>(responseHandler.createHttpException(it))
	}
}


fun <T : Response<*>> Maybe<T>.catchHttpError(responseHandler: ResponseHandler): Maybe<T> = flatMap {
	if (responseHandler.isSuccess(it)) {
		Maybe.just(it)
	} else {
		Maybe.error<T>(responseHandler.createHttpException(it))
	}
}


fun <T : Response<*>> Flowable<T>.catchHttpError(responseHandler: ResponseHandler): Flowable<T> = flatMap {
	if (responseHandler.isSuccess(it)) {
		Flowable.just(it)
	} else {
		Flowable.error<T>(responseHandler.createHttpException(it))
	}
}


fun <T : Response<*>> Observable<T>.catchHttpError(responseHandler: ResponseHandler): Observable<T> = flatMap {
	if (responseHandler.isSuccess(it)) {
		Observable.just(it)
	} else {
		Observable.error<T>(responseHandler.createHttpException(it))
	}
}