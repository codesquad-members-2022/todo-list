package com.codesquad.aos.todolist.repository.log

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codesquad.aos.todolist.data.model.LogX
import com.codesquad.aos.todolist.network.ApiClient
import java.lang.Exception

class LogDataSource(
    private val api: ApiClient
): PagingSource<Int, LogX>() {
    override fun getRefreshKey(state: PagingState<Int, LogX>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(defaultDisplay) ?: anchorPage?.nextKey?.minus(defaultDisplay)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LogX> {

        val start = params.key ?: defaultStart  // params에 key 값 있으면 사용 , 없으면 디폴트 사용,   start = 검색 시작 위치

        return try {
            val response = api.getLogs(start, params.loadSize)
            Log.d("AppTest", "로그 데이터 요청")

            val items = response   // 아이템 리스트
            val nextKey = if (items.isEmpty()) {
                null   // 더 이상 load 할 것 없으면 null
            } else {
                start + params.loadSize

            }
            val prevKey = if (start == defaultStart) {
                null
            } else {
                start - defaultDisplay
            }
            LoadResult.Page(items, prevKey, nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)  // 로드 실패 시 LoadResult.Error 객체 반환
        }
    }


    companion object {
        const val defaultStart = 0
        const val defaultDisplay = 5
    }
}