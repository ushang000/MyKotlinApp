package cn.ushang.joke

import java.io.Serializable

/**
 * Created by ushang000 on 2017/9/25.
 */
data class CommentUser(val commentImg:String?=null,val commentName:String?=null,
                       val commentContent:String?=null,val floor:String?=null):Serializable