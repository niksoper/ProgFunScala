package streams

import streams._

object play {
  
  new StringParserTerrain with Solver {
  
    val level = """|oSoooooT
                   |oooooooo
                   |oooooooo""".stripMargin
    
    ((pathsFromStart take 1).toList) foreach println
  
  }                                               //> (Block(Pos(0,2),Pos(0,3)),List(Right))
                                                  //| res0: streams.StringParserTerrain with streams.Solver{} = streams.play$$anon
                                                  //| fun$main$1$$anon$1@34f5690d
  
}