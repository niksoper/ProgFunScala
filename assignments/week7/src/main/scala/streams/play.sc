package streams

import streams._

object play {
  
  new StringParserTerrain with Solver {
  
    val level = """|oSooT
                   |ooooo""".stripMargin
    
    println(solution)
    
  
  }                                               //> --------------------
                                                  //| (Block(Pos(0,4),Pos(0,4)),List(Right, Right))
                                                  //| (Block(Pos(1,2),Pos(1,3)),List(Down, Right))
                                                  //| --------------------
                                                  //| --------------------
                                                  //| (Block(Pos(0,2),Pos(0,3)),List(Left, Right, Right))
                                                  //| (Block(Pos(1,1),Pos(1,1)),List(Left, Down, Right))
                                                  //| (Block(Pos(1,4),Pos(1,4)),List(Right, Down, Right))
                                                  //| (Block(Pos(0,2),Pos(0,3)),List(Up, Down, Right))
                                                  //| --------------------
                                                  //| --------------------
                                                  //| --------------------
                                                  //| List((Block(Pos(0,2),Pos(0,3)),List(Right)), (Block(Pos(0,4),Pos(0,4)),List(
                                                  //| Right, Right)), (Block(Pos(1,2),Pos(1,3)),List(Down, Right)), (Block(Pos(0,2
                                                  //| ),Pos(0,3)),List(Left, Right, Right)), (Block(Pos(1,1),Pos(1,1)),List(Left, 
                                                  //| Down, Right)), (Block(Pos(1,4),Pos(1,4)),List(Right, Down, Right)), (Block(P
                                                  //| os(0,2),Pos(0,3)),List(Up, Down, Right)))
                                                  //| List()
                                                  //| res0: streams.StringParserTerrain with streams.Solver{} = streams.play$$anon
                                                  //| fun$main$1$$anon$1@52bd62c3
  
}