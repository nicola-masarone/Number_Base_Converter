import java.math.BigInteger

fun main() {
    val a = BigInteger(readLine()!!)
    val b = BigInteger(readLine()!!)

    val (q, r) = a.divideAndRemainder(b)
    println("$a = $b*$q + $r")
}