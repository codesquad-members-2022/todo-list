package com.codesquad.aos.todolist.repository.log

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codesquad.aos.todolist.data.model.LogX
import com.codesquad.aos.todolist.network.ApiClient
import kotlinx.coroutines.flow.Flow

class LogRepository(private val api: ApiClient) {

    fun getLogs(): Flow<PagingData<LogX>> {
        return Pager(
            PagingConfig(
                pageSize = LogDataSource.defaultDisplay,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LogDataSource(api)
            }
        ).flow
    }
}