package subtask5

import kotlin.reflect.KClass

class TelephoneFinder {

    private val keyNumberMap: Map<Char, List<Int> > = mapOf('1' to  listOf(2, 3), '2' to listOf(1,3,5),
        '3' to listOf(2, 6), '4' to listOf(1, 5, 7), '5' to listOf(2, 4, 6, 8), '6' to listOf(3, 5, 9),
        '7' to listOf(4, 8), '8' to listOf(0, 5, 7, 9), '9' to listOf(6, 8), '0' to listOf(8))

    private val numbers = listOf<Char>('0','1','2','3','4','5','6','7','8','9')

    fun findAllNumbersFromGivenNumber(number: String): Array<String>? {

        val resultArray = mutableListOf<String>()
        for (i in number.indices){

            if (number[i] !in numbers) return null

            for(neighbor in keyNumberMap[number[i]]!!){
                val copiedNumber = "${number.substring(0, i)}$neighbor${number.substring(i + 1)}"
                resultArray.add(copiedNumber)
            }
        }

        return if (resultArray.isEmpty()) null
        else resultArray.toTypedArray()
    }
}
