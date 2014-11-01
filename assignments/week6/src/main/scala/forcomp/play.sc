package forcomp

import Anagrams._

object play {
  
 
  def removeAt[T](index: Int, list: List[T]): List[T] = list diff List(list(index))
                                                  //> removeAt: [T](index: Int, list: List[T])List[T]

 
  def reduceHeadCount(pairs: List[(Char, Int)]): List[List[(Char, Int)]] = pairs match {
  
    case Nil => Nil
    case _ => {
    
      val reducedPairs = for {
        p <- pairs
        n <- p._2 to 1 by -1
      } yield (p._1, n)
      
    
      print("horse: ")
      println(reducedPairs)
    
    	Nil
    
    }
  
  }                                               //> reduceHeadCount: (pairs: List[(Char, Int)])List[List[(Char, Int)]]
  
  val pairs = List(('a', 2), ('b', 2), ('c', 3))  //> pairs  : List[(Char, Int)] = List((a,2), (b,2), (c,3))

  combinations(pairs) foreach println             //> List((b,1), (c,2))
                                                  //| List((b,1), (c,1))
                                                  //| List((b,1), (c,3))
                                                  //| List((a,1), (b,2), (c,1))
                                                  //| List((c,1))
                                                  //| List((a,1))
                                                  //| List((b,1))
                                                  //| List((a,1), (c,1))
                                                  //| List((a,2), (b,1), (c,3))
                                                  //| List((b,2))
                                                  //| List((a,1), (b,1))
                                                  //| List((b,2), (c,1))
                                                  //| List((a,2), (c,2))
                                                  //| List((b,2), (c,3))
                                                  //| List()
                                                  //| List((a,2), (b,2), (c,3))
                                                  //| List((a,2), (c,1))
                                                  //| List((a,2), (b,1), (c,1))
                                                  //| List((a,1), (b,2), (c,2))
                                                  //| List((a,2), (b,2), (c,1))
                                                  //| List((a,1), (b,1), (c,1))
                                                  //| List((a,1), (b,1), (c,3))
                                                  //| List((a,2), (b,1))
                                                  //| List((a,2), (b,2), (c,2))
                                                  //| List((b,2), (c,2))
                                                  //| List((a,2), (b,1), (c,2))
                                                  //| List((c,2))
                                                  //| List((a,1), (c,2))
                                                  //| List((a,2))
                                                  //| List((a,1), (b,2))
                                                  //| List((a,2), (b,2))
                                                  //| List((c,3))
                                                  //| List((a,2), (c,3))
                                                  //| List((a,1), (b,1), (c,2))
                                                  //| List((a,1), (c,3))
                                                  //| List((a,1), (b,2), (c,3))

}