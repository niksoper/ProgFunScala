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
  
  val pairs = List(('a', 2), ('b', 2))            //> pairs  : List[(Char, Int)] = List((a,2), (b,2))

  reduceHeadCount(pairs) //foreach { println }    //> horse: List((a,2), (a,1), (b,2), (b,1))
}
  //reduceHeadCount(removeAt(0, pairs)) foreach {println}
  //reduceHeadCount(removeAt(1, pairs)) foreach {println}

}