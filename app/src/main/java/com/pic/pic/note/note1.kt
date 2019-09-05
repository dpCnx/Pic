package note

const val b = 10   //相当于是一个常量 ==》  java里面的 private static int b = 10（编译时候的不可变，不能修改）

class Note {

    val x = 10  //这是运行时的不可变   可以通过反射修改


    //二元元组
    fun pairDemo() {

//        val pair = Pair<String, Int>("张三", 20)

        val pair = "zhangsan" to 20

        pair.first

        pair.second
    }

    //三元元组
    fun tripleDemo() {

        val triple = Triple<String, Int, Int>("张三", 10, 10)

        triple.first

        triple.second

        val third = triple.third
    }

    fun nullDemo() {

        // ?: Elvis 操作符    ==》相当于java里面的三元运算符
        //!! 断言(关闭空检查) ==》告诉编译器不用检查了  一定不会空  （不建议使用）
        // ?. 空安全调用符 （返回的值其实就是可空类型） ==> 相当于 java 里面的
//                                                if(a!=null){
//                                                    //dosometing
//                                                }
        val str: String? = null
        val b: Int = str?.toInt() ?: -1  //如果str为空的时候返回-1  不为空的时候返回str  //相当于java里面的三元运算符

    }


    fun xunHaunDemo() {

        val str = "abcf"

        for (c in str) {
            println(c)
        }

        for ((index, c) in str.withIndex()) {
            println("index = $index + c = $c")
        }

        str.forEach {
            println(it)
        }

        str.forEachIndexed { index, c ->
            println("index = $index + c = $c")
        }

    }

    fun tagDemo() {
        val str1 = "abc"
        val str2 = "123"

        tag@ for (c1 in str1) {
            for (c2 in str2) {
                if (c1 == 'b' && c2 == '2') {
                    break@tag    //结束掉标记的tag
                }
            }
        }
    }

    fun whileAnddoWhileDemo() {

        var a = 0

        while (a < 100) {
            println("s")
            a++
        }

        //先执行一次print
        do {
            print("s")
            a++
        } while (a < 100)
    }

    //区间
    fun rangeDemo() {
        //正常区间
        val range1 = 1..100  //包含 1和100
        val range2 = IntRange(1, 100)
        val range3 = 1.rangeTo(100)
        //反向区间
        val range4 = 100 downTo 1

        val range5 = 1 until 100   //[1, 100)

        for ((index, i) in (1 until 100).withIndex()) {

        }
    }

    fun shuzuDemo() {

        val arr = arrayOf("a", "b", "c")

        val arr1 = arrayOf("a", 10, 'c')  //此时的类型为Any类型  类似Java的object

        val arr2 = IntArray(10)  //new int[10]
        val arr3 = IntArray(10) { 30 }  //数组里面的每一个元素都初始化成30

        val arr4 = Array(20, { "" }) //创建一个固定长度的为20大小的数组，默认值为""
    }

    fun whenDemo(age: Int) {

        when (age) {
            is Int -> "do some"  //is 相当于java的instanceof
            in 1..7 -> "do some"
            8, 9 -> "do some"
            else -> "do some"
        }
    }

//    fun hanshuDemo() {
//
//        val addtwo = ::add  //函数的引用  把变量赋值成为一个函数(这里由于版本太低 没有编译成功)
//        addtwo?.invoke(1, 2) //执行add函数 --> 可以处理函数为kong的时候的调用 invoke调用可以定义可空类型
//        addtwo(1, 2)//执行add函数
//    }
//
//    fun add(a: Int, b: Int) = a + b


    fun 默认参数() {

        add(1, 2)
        add(3)
        add(b = 3, a = 5)  //具名参数，可以调换参数的位置
    }

    fun add(a: Int, b: Int = 5) { //默认参数
        println("a+$a ====b+$b")
    }

    fun 可变参数() {
        addRes(1)
        addRes(1, 2)
        addRes(1, 3, 4)
    }

    fun addRes(vararg a: Int): Int {  //vararg 可变参数
        var res = 0
        a.forEach {
            res += it
        }
        return res
    }

    tailrec fun 尾递归优化(a: Int, res: Int): Int {  //tailrec 尾递归优化 (当调用的递归只有自身而没有其他的操作的时候可以使用)
        return if (a == 1) {
            res + 1
        } else {
            尾递归优化(a - 1, res + a)
        }
    }

    fun 运算符重载() {

        val gril1 = Gril()

        val gril2 = Gril()

        gril1 + gril2  //相当也调的Gril的plus方法
        gril1.plus(gril2)
    }

    class Gril {

        operator fun plus(gril: Gril): Int {  //重载+
            return this.age + 2
        }

        var age = 20
            private set //私有set方法

        var name = "hello"
            set(value) { //重写set方法
                if (!value.equals("19")) {
//                    this.name = value//这样子调用会出现栈内存溢出
                    field = value //field 代表当前字段的set方法
                }else{
                    field = "19"
                }
            }
    }

    //主购函数
    class Person(var name: String, var age: Int) {

        //初始化
        init {

        }

        var phone = ""

        //次购函数
        constructor(name: String, age: Int, phone: String) : this(name, age) {
            this.phone = phone
        }

        //初始化是最先执行的  然后才走的次购函数
    }


    //this关键字
    class OutClass {
        val name = "zs"

        inner class InnerClass {

            val name = "ls"

            fun sayHello() {
                println("name = ${this@OutClass.name}")
            }
        }
    }

    open class Box<T>(var thing: T)

    class Fruits(a: Int) : Box<Int>(a)

    class Others<T>(x: T) : Box<T>(x)

    inline fun <reified D : String> fanxingDemo(b: D) {  //D : String 指定泛型的上线
        var m = D::class.java.name //获取泛型的类型 首先加上inline 然后加上reified
    }

}

fun main(args: Array<String>) {

    val list = ArrayList<Apple>()

    getSomeThing(list)
}

fun getSomeThing(list: ArrayList<out Fruits>) { //out 关键字可以让它的子类传进去   相当于java里面的 ？ extents
    println()
}

fun getSomeThing1(list: ArrayList<in Fruits>) { //in 关键字可以让它的父类传进去   相当于java里面的 ？ super
    println()
}

fun getSomeThing2(list: ArrayList<*>) { //*可以接受任何类型 相当于java里面的？
    println()
}

open class Fruits
class Apple : Fruits()
class Orienge : Fruits()