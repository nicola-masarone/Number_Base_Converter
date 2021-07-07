import java.math.BigInteger

fun main() {
    val exaBytes = BigInteger(readLine()!!)
    println(exaBytes * BigInteger("9223372036854775808"))
}