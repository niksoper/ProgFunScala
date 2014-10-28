package forcomp

import Anagrams._

object play {
  
  val abba = List(('a', 2), ('b', 2))             //> abba  : List[(Char, Int)] = List((a,2), (b,2))
 
  combinations(abba)                              //> res0: List[forcomp.Anagrams.Occurrences] = List(List())
 
 for {
   pair <- abba
   n <- pair._2 to 1 by -1
 
 } yield (pair._1, n)                             //> res1: List[(Char, Int)] = List((a,2), (a,1), (b,2), (b,1))

}