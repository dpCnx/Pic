package note

/**
 * DSL--> class
 */
fun main(args: Array<String>) {


    val person: MyPerson = myperson {

        name = "zhangsan"
        age = 20
        address {
            shiqu = "cq"
        }

    }
}

fun MyPerson.address(block: Address.() -> Unit): Address {
//    val address = Address()
//    address.block()
//    this.address = address
//    return address

    return this.address(block)
}

fun myperson(block: MyPerson.() -> Unit): MyPerson {
//    val myperson = MyPerson()
//    myperson.block()
//    return myperson

    return MyPerson().apply(block)
}

data class Address(var shiqu: String? = null)

data class MyPerson(var name: String? = null, var age: Int? = null, var address: Address? = null)

class MyPersonBuilder {

}

