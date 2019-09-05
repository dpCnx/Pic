package note

/**
 * lambda表达式与高级函数
 */
fun main(args: Array<String>) {

    val a = 10
    val b = 10

    //{} 称为lambda表达式 不需要定义返回值的类型 最后一行就相当于返回值
    var c = scale(a, b, { m, n ->
        m + n
    })

    //当参数的最后一个函数是lambda表达式的时候，括号可以前移

    var d = scale(a, b) { m, n ->
        m + n
    }

}

//函数里面传函数就是高级函数
fun scale(a: Int, b: Int, util: (Int, Int) -> Int): Int {
    return util(a, b)
}


fun lambdaDemo() {

    //lambda表达式单独调用-----------------------------------------------

    {
        println("hello")
    }

    //此时相当于只是定义了这个匿名函数

//    {
//        println("hello")
//    }()

    //加上括号才相当于在调用

    {
        println("hello")
    }.invoke()
}

fun lamdaDemo2() {

    val res: Int = { a: Int, b: Int ->
        a + b
    }(2, 3)

    val res2: () -> Unit = {
        println("s")
    }

    res2()

    res2.invoke()
}

fun lamdaDemo3() {

    val list = mutableListOf<String>()

    //过滤器
    val list2 = list.filter {
        it.startsWith("a")
    }
}