package cn.ushang.joke

import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ushang000 on 2017/9/19.
 */
interface JokeService{
    @GET("/8hr/page/{id}")
    fun getContent(@Path("id") id : Int): Call<Any>
}
object ApiClient{
    val retrofit : JokeService by lazy{
        Retrofit.Builder().baseUrl("http://www.qiushibaike.com")
                //.addConverterFactory(Converte)
                .build().create(JokeService::class.java)
    }
}

fun main(args: Array<String>) {
    val result=ApiClient.retrofit.getContent(1).execute().body()
    println(result)
}
