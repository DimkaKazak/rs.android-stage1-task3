package subtask4

class SquareDecomposer {

    fun decomposeNumber(number: Int): IntArray? {

        val tempArray = decomposer(number , number * number)

        if(tempArray.isEmpty()) return null
        tempArray.removeAt(tempArray.size - 1)
        return tempArray.toIntArray()
    }

    private fun decomposer(number: Int, remain: Int): MutableList<Int>{
        if (remain == 0){
            return  mutableListOf(number)
        }

        for ( i in (number - 1) downTo 0 ){
            if( remain - i * i >= 0){
                val remainers = decomposer(i, (remain - i * i))

                if (remainers.isNotEmpty()){
                    remainers.add(number)
                    return remainers
                }
            }
        }

        return mutableListOf()
    }

}
