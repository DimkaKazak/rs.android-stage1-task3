package subtask3

import java.lang.Math.abs

class ArrayCalculator {

    fun maxProductOf(numberOfItems: Int, itemsFromArray: Array<Any>): Int {

        val resultArray: MutableList<Int> = mutableListOf()
        for (currElement in itemsFromArray){
            if (currElement is Int) resultArray.add(currElement)
        }

        if (resultArray.isEmpty()) return 0

        if (numberOfItems > resultArray.size) {
            return productAllElem(resultArray)
        }

        resultArray.sortWith(Comparator<Int> { p0, p1 ->
            when{
                abs(p0!!) > abs(p1!!) -> -1
                abs(p0) == abs(p1) -> 0
                else -> 1
            }
        })

        var resultProd = 1
        var counter = 0

        var prevProd = 1
        var prevCounter = 0

        var hasNegativePair = true
        var copiedNumOfItems = numberOfItems
        while (counter != copiedNumOfItems){
            if (counter == resultArray.size) return resultProd

            if (resultArray[counter] < 0){

                resultProd *= resultArray[counter]
                hasNegativePair = resultProd >= 0

                if (!hasNegativePair){
                    prevProd = resultProd / resultArray[counter]
                    prevCounter = counter
                }
            } else {
                resultProd *= resultArray[counter]
            }

            counter++

            if (counter == numberOfItems && !hasNegativePair){
                counter = prevCounter + 1
                copiedNumOfItems++
                resultProd = prevProd
                hasNegativePair = true
            }
        }

        return resultProd
    }

    private fun productAllElem(array: List<Int>): Int{
        var resultProd = 1
        for (elem in array){
            resultProd *= elem
        }
        return  resultProd
    }

}
