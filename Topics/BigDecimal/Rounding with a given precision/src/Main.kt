import java.math.BigDecimal
import java.math.RoundingMode

fun main() {             
    val num = BigDecimal(readLine()!!)
    val newScale = readLine()!!.toInt()
    println(num.setScale(newScale, RoundingMode.HALF_DOWN))
}