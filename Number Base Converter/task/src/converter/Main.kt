package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

fun fromDecimal(decNum: String, targetBase: String): String {
    var resultString = ""
    // Integer part conversion
    var bigNumInt = decNum.substringBefore(".").toBigInteger()
    do {
        val remainder = bigNumInt.remainder(targetBase.toBigInteger()).toInt()
        if (remainder > 9) {
            resultString += 'a' + (remainder - 10)
        } else {resultString += remainder.toString()}
        bigNumInt /= targetBase.toBigInteger()
    } while ( bigNumInt > BigInteger.ZERO )
    resultString = resultString.reversed()  // Integer part needs string inversion

    // Fractional part conversion
    var srcFracPart = decNum.substringAfter(".")
    if (srcFracPart != decNum) {
        resultString += "."
        var decNumBig = "0.$srcFracPart".toBigDecimal()
        var digits = 5
        while (digits > 0) {
            digits--
            decNumBig *= targetBase.toBigDecimal()
            val intPart = decNumBig.toInt()
            resultString += if (intPart > 9) { 'a' + (intPart - 10) }
            else { intPart.toString() }
            decNumBig -= intPart.toBigDecimal()
        }
    }
    return resultString
}

fun toDecimal(sourceNum: String, sourceBase: String): String {
    // Integer part conversion
    var srcIntPart = sourceNum.substringBefore(".")
    var resultNum = BigDecimal.ZERO
    var pow = BigDecimal.ONE
    for (i in srcIntPart.lastIndex downTo 0) {
        val digit = srcIntPart[i]
        if (digit.isLetter()) {
            resultNum += ((digit - 'a').toBigDecimal() + BigDecimal.TEN) * pow
        } else { resultNum += digit.toString().toBigDecimal() * pow }
        pow *= sourceBase.toBigDecimal()
    }
    // Fractional part conversion
    var srcFracPart = sourceNum.substringAfter(".")
    if (srcFracPart != sourceNum) {
        pow = BigDecimal.ONE * sourceBase.toBigDecimal()
        for (i in srcFracPart.indices) {
            val digit = srcFracPart[i]
            if (digit.isLetter()) {
                resultNum += ((digit - 'a').toBigDecimal() + BigDecimal.TEN).setScale(10) / pow
            } else { resultNum += digit.toString().toBigDecimal().setScale(10) / pow }
            pow *= sourceBase.toBigDecimal()
        }
    }
    return resultNum.toString()
}

fun main() {
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val selBaseTarget = readLine()!!
        if (selBaseTarget == "/exit") {
            break
        }
        val (sourceBase, targetBase) = selBaseTarget.split(" ")
        while (true) {
            print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
            val sourceNum = readLine()!!
            if (sourceNum == "/back") {
                break
            }
            val decRes = toDecimal(sourceNum, sourceBase)
            val convResult = fromDecimal(decRes, targetBase)
            println("Conversion result: $convResult")
        }
    }
}