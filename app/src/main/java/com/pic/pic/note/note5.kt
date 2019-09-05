package note

/**
 * 四大函数
 */
fun main(args: Array<String>) {
    val list: ArrayList<String>? = arrayListOf()

    //-------------apply---------------------
    //任意类型都有apply类型
    //apply参数是一个函数
    //返回值就是自己本身
    list?.apply {
        add("a")
    }

    //-----------------let-------------------
    //let函数的返回值是函数参数的返回值
    val res: Boolean? = list?.let {
        it.add("a")
    }
    //-----------------with-----------------
    //with函数的返回值是第二个参数的返回值 和let类似
    with(list, {
        this?.add("a")
    })

    with(list) {
        this?.add("a")
    }

    //--------------------run----------------------
    //接收者是调用者本身
    //返回值是参数的返回值
    list?.run {
        add("s")
    }

    //------------------------------------------------------接口回掉--------------------------

    val sm = SuperMarket()
    sm.getRoy {
    }
}

class SuperMarket {

    fun getRoy(block: (Roy) -> Unit) {

        val roy = Roy()

        block.invoke(roy)
    }
}

class Roy

