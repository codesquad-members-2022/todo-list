package com.example.todo_list.history.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.model.HistoryCard
import com.example.todo_list.model.HistoryReceive
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryRepository {
    private val retrofit = HistoryReceive.service
    private val historyList = MutableLiveData<List<HistoryCard>>()

    fun getHistory(): LiveData<List<HistoryCard>> {
        retrofit.getHistory("histories").enqueue(object : Callback<List<HistoryCard>> {
            override fun onResponse(call: Call<List<HistoryCard>>, response: Response<List<HistoryCard>>) {
                historyList.value = response.body()
            }
            override fun onFailure(call: Call<List<HistoryCard>>, t: Throwable) {
                Log.d("onFailure", "${t.message}")
            }
        })
        return historyList
    }
}