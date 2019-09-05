package note

import kotlin.reflect.KProperty

/**
 * Created by admin on 2018/5/21.
 */
fun main(args: Array<String>) {

    val p = Person()

    p.sayHello("s")

    p sayHello "s"//中缀表达式的效果

//    ----------------------------------------------------------------------

    val smallHeadDad = SmallHeadDad(BigHeadSon())
    smallHeadDad.wash()

    //    ----------------------------------------------------------------------

    val son = son()
    son.SayHello()

    //    ----------------------------------------------------------------------

    WEEK.FIVE.name

    WEEK.FIVE.ordinal

    COLOR.BULE.b

}

class Person {
    infix fun sayHello(name: String) {  //中缀表达式 ==》 必须是成员函数  只能有一个参数 参数不能是可变参数 和默认参数
        println("s")
    }
}

interface WashPower {
    fun wash()
}

class BigHeadSon : WashPower {
    override fun wash() {
        println("son")
    }

}

class SmallHeadDad(var washPower: WashPower) : WashPower by washPower {  //by关键字 委托类
    override fun wash() {

        println("come")

        washPower.wash()

        println("compelete")
    }
}


class SmallBoy {
    var 压岁钱: Int by SmallMother()  //属性的委托 (相当于把set get方法让给mother使用)
}

class SmallMother {

    var 妈妈压岁钱 = 0

    operator fun getValue(smallBoy: SmallBoy, property: KProperty<*>): Int {
        return 妈妈压岁钱
    }

    operator fun setValue(smallBoy: SmallBoy, property: KProperty<*>, i: Int) {
        妈妈压岁钱 = i
    }

}

class LazyAndlateinit {

    val name by lazy { "name" } //惰性加载

    lateinit var name1: String //延迟加载
}

//扩展函数

fun Int.Isnull() {  //不可空

}

fun Int?.Isnull() { //可空

}

//扩展函数 父类的扩展函数 子类可以调用
open class father {
    companion object {  //半生对象 控制属性的静态 里面的字段都是静态的

    }
}

class son : father()

fun father.SayHello() {
    println("hello")
}

object Utils { //单例模式 里面的字段都是静态的 这种模式的单利  在字段太多的时候 全都是static的 对性能不好
    val s = ""
}

class Utils2 private constructor() {  //创建和java一样的单例模式

    companion object {
        val instans by lazy { Utils2() }  //惰性加载 只会加载一次 线程安全（by lazy 是线程安全的）
    }
}

//枚举
enum class WEEK {
    ONE, TWO, THREE, FORE, FIVE
}

//枚举的高级用法
enum class COLOR(var r: Int, var g: Int, var b: Int) {
    RED(255, 0, 0), BULE(255, 0, 0), GREEN(255, 0, 0)
}

data class DataDemo(var a: String, var b: String, var c: String)

fun DataDemoEg() {

    val data = DataDemo("a", "b", "c")
    data.a

    data.component1() //第一个元素
    data.component2() //第二个元素
    data.component3() //第三个元素

    //解构
    val (a, b, c) = DataDemo("a", "b", "c")

    //使用
    println(a)
}

//密封类
sealed class Farher {

    class mySonOne : Farher()
    class mySonTwo : Farher()
    class mySonThree : Farher()
    class mySonFour : Farher()
}

class NoMySon : Farher()

fun SealeDemo(x: Farher): Boolean {
    return when (x) {
        is Farher.mySonOne -> true
        is Farher.mySonTwo -> true
        is Farher.mySonThree -> true
        is Farher.mySonFour -> true
        else -> false
    }
}

