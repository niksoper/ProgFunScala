package funsets

object play {

    val oneToTen = FunSets.rangeSet(1, 10)        //> oneToTen  : Int => Boolean = <function1>
    val twoToEleven = FunSets.rangeSet(2, 11)     //> twoToEleven  : Int => Boolean = <function1>
    
    val mapped = FunSets.map(oneToTen, x => x + 1)//> mapped  : Int => Boolean = <function1>
  FunSets.toString(oneToTen)                      //> res0: String = {1,2,3,4,5,6,7,8,9,10}
  FunSets.toString(mapped)                        //> res1: String = {0,1,2,3,4,5,6,7,8,9}
 
}