package cz.helu.helublocks.extension

import android.content.SharedPreferences
import java.util.*
import kotlin.collections.HashSet


inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
	val editor = this.edit()
	operation(editor)
	editor.apply()
}


@Suppress("UNCHECKED_CAST")
inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
	return when (T::class) {
		Date::class -> Date(getLong(key, defaultValue as? Long ?: -1)) as T?
		String::class -> getString(key, defaultValue as? String) as T?
		Set::class -> getStringSet(key, defaultValue as? Set<String>) as T?
		HashSet::class -> getStringSet(key, defaultValue as? HashSet<String>) as T?
		Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
		Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
		Long::class -> getLong(key, defaultValue as? Long ?: -1L) as T?
		Float::class -> getFloat(key, defaultValue as? Float ?: -1F) as T?
		else -> throw UnsupportedOperationException("Unsupported preference type")
	}
}


@Suppress("UNCHECKED_CAST")
operator fun SharedPreferences.set(key: String, value: Any?) {
	when (value) {
		is Date -> edit { it.putLong(key, value.time) }
		is String? -> edit { it.putString(key, value) }
		is Set<*> -> edit { it.putStringSet(key, value as Set<String>) }
		is HashSet<*> -> edit { it.putStringSet(key, value as HashSet<String>) }
		is Boolean -> edit { it.putBoolean(key, value) }
		is Int -> edit { it.putInt(key, value) }
		is Long -> edit { it.putLong(key, value) }
		is Float -> edit { it.putFloat(key, value) }
		else -> throw UnsupportedOperationException("Unsupported preference type")
	}
}