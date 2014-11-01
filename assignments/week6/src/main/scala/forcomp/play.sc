package forcomp

import Anagrams._

object play {

  val w = "Hello"                                 //> w  : String = Hello
  w foreach println                               //> H
                                                  //| e
                                                  //| l
                                                  //| l
                                                  //| o
  w groupBy (c => c) map (p => (p._1 -> p._2.length))
                                                  //> res0: scala.collection.immutable.Map[Char,Int] = Map(e -> 1, l -> 2, H -> 1,
                                                  //|  o -> 1)
  w.toList groupBy (c => c)                       //> res1: scala.collection.immutable.Map[Char,List[Char]] = Map(e -> List(e), l 
                                                  //| -> List(l, l), H -> List(H), o -> List(o))
  

}