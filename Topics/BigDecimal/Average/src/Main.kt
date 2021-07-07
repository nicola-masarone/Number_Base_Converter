import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val a = BigDecimal(readLine()!!)
    val b = BigDecimal(readLine()!!)
    val c = BigDecimal(readLine()!!)

    val avg = (a + b + c).setScale(0, RoundingMode.DOWN) / 3.toBigDecimal()
    println(avg)
}