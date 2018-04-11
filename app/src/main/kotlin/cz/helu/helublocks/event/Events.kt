package cz.helu.helublocks.event

import org.alfonz.arch.event.Event

// TT Global events

class SnackbarEvent(val message: String) : Event()
class ToastEvent(val message: String) : Event()
class DataChangeEvent : Event()