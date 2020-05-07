package subtask2

import java.math.BigInteger

class Combinator {

    fun checkChooseFromArray(array: Array<Int>): Int? {

        var currBinomial: BigInteger = BigInteger.ZERO
        val n: Int = array[1]
        var m: Int = 0
        while (currBinomial.toInt() < array[0]){
            m++
            if (m > n) break
            currBinomial = binomialCoef(n, m)
        }

        return if (currBinomial.toInt() == array[0]) m
        else null
    }

    private fun factorial(number: Int): BigInteger{
        var result = BigInteger.ONE
        for (i in 1..number){
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        return result
    }

    private fun binomialCoef(n: Int, m: Int): BigInteger {
        return factorial(n)/ (factorial(m) * factorial(n - m))
    }

}
