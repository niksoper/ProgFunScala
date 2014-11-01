package forcomp

import Anagrams._

object play {

  val pairs = List(('a', 2), ('b', 2), ('c', 3))  //> pairs  : List[(Char, Int)] = List((a,2), (b,2), (c,3))
  val abba = List(('a', 2), ('b', 2))             //> abba  : List[(Char, Int)] = List((a,2), (b,2))

  subtract(pairs, abba)                           //> res0: forcomp.Anagrams.Occurrences = List((c,3))


}