package subtask6

import kotlin.math.floor
import kotlin.math.log2
import kotlin.math.pow

class FullBinaryTrees {

    fun stringForNodeCount(count: Int): String {

        if (count % 2 == 0) return "[]"
        if (count == 1) return "[[0]]"

        val maxLevel = (floor(count.toFloat() / 2) + 1).toInt()
        val maxArraySize = (2.0.pow(maxLevel) - 1).toInt()


        var tempArray = mutableListOf< List<Int> >( listOf(0,0,0) )

        var isFinished = false
        while (!isFinished){
            var copiedArray = tempArray

            for (array in tempArray){
                copiedArray = (copiedArray + getCombinations(array.toTypedArray())).toMutableList()
            }

            for (array in copiedArray){
                if ( array.size > maxArraySize){
                    isFinished = true
                }
            }

            tempArray = copiedArray
        }

        var copiedArray = mutableListOf< List<Int> >()
        for (tree in tempArray){
            if(validateTree(tree.toTypedArray(), count)) copiedArray.add(tree)
        }

        var resultArray = copiedArray.toSet().toTypedArray()
        var rescaledResultArray = mutableSetOf< Array<Int> >()
        for (tree in resultArray){
            rescaledResultArray.add( deleteNullChildren( deleteNullLevel( tree.toTypedArray() ).toMutableList() ).toTypedArray() )
        }

        var resultString = mutableListOf<String>()

        for(i in 0 until rescaledResultArray.size){
            resultString.add(arrayToString(rescaledResultArray.elementAt(i)))
        }

        return "[${resultString}]"
    }


    private fun getCombinations(tree: Array<Int>): List< List<Int> >{

        val copiedTree = addLevel( tree.copyOf() )
        val levelsNumber = log2(copiedTree.size.toFloat() + 1).toDouble() - 1

        val firstNodeOfLevelBeforeLast = 2.0.pow( levelsNumber - 1).toInt() - 1
        val firstNodeOfLastLevel = 2.0.pow( levelsNumber ).toInt() - 1

        val resultList = mutableListOf< List<Int> >()
        for (i in firstNodeOfLevelBeforeLast..firstNodeOfLastLevel){
            var copiedArray = copiedTree

            for (j in firstNodeOfLevelBeforeLast until i){
                copiedArray = addNode(copiedArray, j)
            }

            for (k in i until firstNodeOfLastLevel){
                val tempArray = addNode(copiedArray, k).toList()
                resultList.add(tempArray)
            }
        }

        return resultList
    }

    private fun addLevel(tree: Array<Int>): Array<Int> {
        val copiedTree: MutableList<Int> = tree.copyOf().toMutableList()
        val levelsNumber = log2(copiedTree.size.toFloat() + 1).toDouble()

        var counter: Double = 0.0
        while ( (2.0.pow(levelsNumber + 1) - 2.0.pow(levelsNumber) - counter) >= 10.0.pow(-5.0)){
            copiedTree.add(-1)
            counter++
        }

        return copiedTree.toTypedArray()
    }

    private fun addNode(tree: Array<Int>, to: Int): Array<Int> {
        val copiedTree = if(2 * to + 1 > tree.size - 1) addLevel(tree.copyOf())
        else tree.copyOf()

        if(copiedTree[to] != -1){
            copiedTree[2 * to + 1] = 0
            copiedTree[2 * to + 2] = 0
        }

        return copiedTree
    }

    private fun validateTree(tree: Array<Int>, count: Int): Boolean{
        var counter = 0

        for (elem in tree){
            if (elem == 0) counter++
        }

        return counter == count
    }

    private fun deleteNullLevel(tree: Array<Int>): Array<Int>{

        val levelsNumber = log2(tree.size.toFloat() + 1).toDouble() - 1
        val firstNodeOfLastLevel = 2.0.pow( levelsNumber ).toInt() - 1

        for( i in firstNodeOfLastLevel until tree.size){
            if (tree[i] == 0) return tree
        }

        val copiedTree = mutableListOf<Int>()

        for ( i in 0 until firstNodeOfLastLevel){
            copiedTree.add(tree[i])
        }

        return copiedTree.toTypedArray()
    }

    private fun deleteNullChildren(tree: MutableList<Int>): MutableList<Int>{
        val levelsNumber = log2(tree.size.toFloat() + 1).toDouble() - 1
        val firstNodeOfLastLevel = 2.0.pow( levelsNumber ).toInt() - 1

        var copiedTree = tree
        for( i in tree.size - 1 downTo  firstNodeOfLastLevel){
            if(copiedTree[i] == 0){
                break
            }
            copiedTree.removeAt(i)
        }

        return copiedTree
    }

    private fun arrayToString(tree: Array<Int>): String{
        var result = "["

        for (i in tree.indices){
            if (tree[i] == 0){
                result = result.plus("0")
            }

            if (tree[i] == -1){
                result = result.plus("null")
            }

            if (i == tree.size - 1) result = result.plus("]\n")
            else result = result.plus(",")
        }

        return result
    }

}
