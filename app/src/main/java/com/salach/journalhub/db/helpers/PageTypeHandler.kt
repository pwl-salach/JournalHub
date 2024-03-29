package com.salach.journalhub.db.helpers

import androidx.lifecycle.LiveData

interface PageTypeHandler<T : PageRepresentation> {
    fun cast(obj: PageRepresentation): T

    suspend fun insert(obj: T)
    suspend fun update(obj: T)
    fun getById(partId: Long): LiveData<T>?
}
