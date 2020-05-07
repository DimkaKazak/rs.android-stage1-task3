package subtask1

class PolynomialConverter {

    fun convertToStringFrom(numbers: Array<Int>): String? {

        if (numbers.isEmpty()) return null
        if (numbers.size == 1){
            return when{
                numbers[0] > 0 -> "${numbers[0]}x"
                numbers[0] < 0 -> "- ${numbers[0]}x"
                else -> ""
            }
        }

        var stringNumArray: Array<String?> = arrayOfNulls(numbers.size)

        for (i in numbers.indices) {
            stringNumArray[i] = ""
            if (numbers[i] == 0) {
                continue
            }

            if (i == 0) {
                if (numbers[i] < 0){
                    stringNumArray[i] = stringNumArray[i].plus("- ")
                    if (numbers[i] != -1) stringNumArray[i] = stringNumArray[i].plus("${-1 * numbers[i]}")
                }
                else{
                    if (numbers[i] != 1) stringNumArray[i] = stringNumArray[i].plus("${numbers[i]}")
                }

                stringNumArray[i] = stringNumArray[i].plus("x^${numbers.size - 1} ")
            }


            if (i == numbers.size - 1) {
                if (numbers[i] < 0) stringNumArray[i] = stringNumArray[i].plus("- ${numbers[i]}")
                else stringNumArray[i] = stringNumArray[i].plus("+ ${numbers[i]}")
            }



            if (i == numbers.size - 2){
                if (numbers[i] < 0){
                    stringNumArray[i] = stringNumArray[i].plus("- ")
                    if (numbers[i] != -1) stringNumArray[i] = stringNumArray[i].plus("${-1 * numbers[i]}")
                }
                else{
                    stringNumArray[i] = stringNumArray[i].plus("+ ")
                    if (numbers[i] != 1) stringNumArray[i] = stringNumArray[i].plus("${numbers[i]}")
                }
                stringNumArray[i] = stringNumArray[i].plus("x ")
            }


            if (i > 0 && i < numbers.size - 2) {
                if (numbers[i] < 0){
                    when {
                        numbers[i] == 1 -> stringNumArray[i] = "- x^${numbers.size - (i + 1)} "
                        else -> stringNumArray[i] = "- ${-1 * numbers[i]}x^${numbers.size - (i + 1)} "
                    }
                }
                else stringNumArray[i] = "+ ${numbers[i]}x^${numbers.size - (i + 1)} "
            }
        }


        var resultString: String = ""

        for (currString in stringNumArray){
            resultString = resultString.plus(currString)
        }

        return resultString
    }

    private fun checkSize(size: Int): Boolean{
        return size == 1
    }

}
