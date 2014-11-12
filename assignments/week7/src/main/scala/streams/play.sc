package streams

import streams._

object play {
  
  new StringParserTerrain with Solver {
  
    val level = """|oSo-T
                   |ooooo
                   |ooooo""".stripMargin
    
    println(solution)
    
  
  }                                               //> List(Down, Right, Right, Right, Up)
                                                  //| res0: streams.StringParserTerrain with streams.Solver{} = streams.play$$anon
                                                  //| fun$main$1$$anon$1@3866ee93
  
}