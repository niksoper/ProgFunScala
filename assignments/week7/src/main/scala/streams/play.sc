package streams

import streams._

object play {
  
  new StringParserTerrain with Solver {
  
    val level = "oSooT"
    
    println(done(startBlock.right.right))
  
  }                                               //> true
                                                  //| res0: streams.StringParserTerrain with streams.Solver{} = streams.play$$anon
                                                  //| fun$main$1$$anon$1@12c50438
  
  val s = Stream(1, 2, 3)                         //> s  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
  s.head                                          //> res1: Int = 1
  s.tail                                          //> res2: scala.collection.immutable.Stream[Int] = Stream(2, ?)
}