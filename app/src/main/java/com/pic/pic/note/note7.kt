package note.xx


/**
 * DSL--> class
 */
fun main(args: Array<String>) {


    val person: note.xx.MyPerson = myperson {

        name = "zhangsan"
        age = 20
        address {
            shiqu = "cq"
        }

    }
}

fun MyPersonBuilder.address(block: MyAddressBuilder.() -> Unit) {

    val address = MyAddressBuilder()
    address.block()
    this.address = address.builder()

}

fun myperson(block: MyPersonBuilder.() -> Unit): note.xx.MyPerson {

    val myperson = MyPersonBuilder()
    myperson.block()
    return myperson.buider()

}

data class Address(val shiqu: String?)

data class MyPerson(val name: String?, val age: Int?, val address: Address?)

class MyPersonBuilder {

    var name: String? = null
    var age: Int? = null
    var address: Address? = null

    fun buider(): note.xx.MyPerson {
        return note.xx.MyPerson(name, age, address)
    }
}

class MyAddressBuilder {

    var shiqu: String? = null

    fun builder(): Address {
        return Address(shiqu)
    }
}