package note

import java.util.*
import kotlin.collections.HashMap

/**
 * Created by admin on 2018/5/23.
 */
fun main(args: Array<String>) {

    //-------------------------------list------------------------------
    val list1 = listOf<String>("a", "b", "c")  //只读的集合 不能修改

    val list2 = arrayListOf<String>()

    val list3 = mutableListOf<String>()
    list3.set(0, "d")


    val list4 = ArrayList<String>()

    val list5 = Vector<String>()

    for ((index, s) in list1.withIndex()) {

    }


    //set集合与list集合 差不多

    //------------------------------map--------------------------------
    val map1 = mapOf<String, String>("a" to "b", "c" to "d")

    val map2: Map<String, String> = mutableMapOf()

    val map3 = HashMap<String, String>()

    val map4 = hashMapOf<String, String>()

    val keys = map1.keys
    keys.forEach { println(it) }

    val values = map1.values

    values.forEach { }

    val entries = map1.entries
    entries.forEach {
        it.key
        it.value
    }

//    mapOf.forEach { t, u ->
//        println("key=$t,value=$u")
//    }

    for ((key: String, value: String) in map1) {

    }

    //-------------------------------------------------------------------------------------------------

    val demoList = listOf("张三", "里斯", "王五", "d")
    val demoList2 = listOf("一", "二", "三", "四")

    //找出第一个带有c的

    demoList.find { it.startsWith("c") }

    //找出第所有带有c的

    demoList.filter { it.startsWith("c") }

    //找出两个集合里面带有C的

    val alllist = mutableListOf<String>()

    demoList.filterTo(alllist, {
        it.startsWith("c")
    })

    demoList2.filterTo(alllist, {
        it.startsWith("c")
    })

    //找角标为偶数的元素

    demoList.filterIndexed { index, _ ->
        index % 2 == 0
    }

    //排序

    demoList.sorted() //正序

    demoList.sortedDescending() //倒叙

    //分组

    val map = demoList.groupBy {

        val first = it.substring(0, 1)
        when (first) {
            "a" -> "a"
            else -> "qita"
        }
    }

    //去重复

    demoList.toSet()

    demoList.distinct()

    demoList.distinctBy { it }

    //集合的拆分

    val pair = demoList.partition { it.startsWith("张") }
    //二元数组的结构
    val (zhang, other) = demoList.partition { it.startsWith("张") }

    pair.first

    pair.second

    //集合重新组合

    val newlist = demoList.map {
        it.startsWith("a")
    }

    //求和

    demoList.sumBy { it.length }

}