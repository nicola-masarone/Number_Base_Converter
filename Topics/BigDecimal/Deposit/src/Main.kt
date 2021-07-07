import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val startingAmount = BigDecimal(readLine()!!)
    val interestRate = BigDecimal(readLine()!!).setScale(2, RoundingMode.CEILING)
    val numYears = readLine()!!.toInt()

    val finalAmount = startingAmount * (BigDecimal.ONE.setScale(2, RoundingMode.CEILING) +
                    interestRate / BigDecimal.valueOf(100).setScale(2, RoundingMode.CEILING))
        .pow(numYears)
    println("Amount of money in the account: " + finalAmount.setScale(2, RoundingMode.CEILING))
}