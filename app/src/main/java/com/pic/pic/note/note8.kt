package note.array

/**
 * DSL--> class
 */
fun main(args: Array<String>) {


    val person: MyPerson = myperson {

        name = "zhangsan"
        age = 20
        addresses {
            address {
                shiqu = "cq"
            }
            address {
                shiqu = "cq"
            }
            address {
                shiqu = "cq"
            }
        }
    }
}

fun ArrayList<Address>.address(block: MyAddressBuilder.() -> Unit) {

    val address = MyAddressBuilder()
    address.block()
    add(address.builder())
}

fun MyPersonBuilder.addresses(block: ArrayList<Address>.() -> Unit) {

    val list = ArrayList<Address>()
    list.block()
    this.address = list
}

fun myperson(block: MyPersonBuilder.() -> Unit): MyPerson {

    val myperson = MyPersonBuilder()
    myperson.block()
    return myperson.buider()

}

data class Address(val shiqu: String?)
data class MyPerson(val name: String?, val age: Int?, val address: ArrayList<Address>?)
@Myzyy
class MyPersonBuilder {

    var name: String? = null
    var age: Int? = null
    var address: ArrayList<Address>? = null

    fun buider(): MyPerson {
        return MyPerson(name, age, address)
    }
}

@Myzyy
class MyAddressBuilder {

    var shiqu: String? = null

    fun builder(): Address {
        return Address(shiqu)
    }
}

@DslMarker
annotation class Myzyy //dsl作用域
