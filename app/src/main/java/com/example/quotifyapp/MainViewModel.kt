package com.example.quotifyapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0
    init {
        quoteList = loadQuoteListFromAsset()
    }
    fun getQuote() = quoteList[index]
    fun nextQuote() : Quote {
        if(index == quoteList.lastIndex){
            index = 0
            return quoteList[index]
        }else{
            return quoteList[++index]
        }
    }
    fun prevQuote(): Quote {
        if(index == 0){
            index = quoteList.lastIndex
            return quoteList[index]
        }else{
            return quoteList[--index]
        }
    }

    private fun loadQuoteListFromAsset(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }
}