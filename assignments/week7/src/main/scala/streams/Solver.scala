package streams

import common._

/**
 * This component implements the solver for the Bloxorz game
 */
trait Solver extends GameDef {

  /**
   * Returns `true` if the block `b` is at the final position
   */
  def done(b: Block): Boolean = b.isStanding && b.b1 == goal

  /**
   * This function takes two arguments: the current block `b` and
   * a list of moves `history` that was required to reach the
   * position of `b`.
   * 
   * The `head` element of the `history` list is the latest move
   * that was executed, i.e. the last move that was performed for
   * the block to end up at position `b`.
   * 
   * The function returns a stream of pairs: the first element of
   * the each pair is a neighboring block, and the second element
   * is the augmented history of moves required to reach this block.
   * 
   * It should only return valid neighbors, i.e. block positions
   * that are inside the terrain.
   */
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] =
    b.legalNeighbors map { case (b, mv) => (b, mv :: history) } toStream

  /**
   * This function returns the list of neighbors without the block
   * positions that have already been explored. We will use it to
   * make sure that we don't explore circular paths.
   */
  def newNeighborsOnly(neighbors: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] =
    neighbors filter { case (b, _) => !explored.contains(b) }
    
    
  /**
   * The function `from` returns the stream of all possible paths
   * that can be followed, starting at the `head` of the `initial`
   * stream.
   * 
   * The blocks in the stream `initial` are sorted by ascending path
   * length: the block positions with the shortest paths (length of
   * move list) are at the head of the stream.
   * 
   * The parameter `explored` is a set of block positions that have
   * been visited before, on the path to any of the blocks in the
   * stream `initial`. When search reaches a block that has already
   * been explored before, that position should not be included a
   * second time to avoid cycles.
   * 
   * The resulting stream should be sorted by ascending path length,
   * i.e. the block positions that can be reached with the fewest
   * amount of moves should appear first in the stream.
   * 
   * Note: the solution should not look at or compare the lengths
   * of different paths - the implementation should naturally
   * construct the correctly sorted stream.
   */
  def from(initial: Stream[(Block, List[Move])],
           explored: Set[Block]): Stream[(Block, List[Move])] = {
    
    if (initial.isEmpty) Stream.empty
    else {
      
      val more = for {
        move <- initial
        next <- newNeighborsOnly(neighborsWithHistory(move._1, move._2), explored)
      } yield next
      
      println("--------------------")
      more.toList foreach println
      println("--------------------")
      
      initial ++ from(more, (more.toList.map { case (b, _) => b } toSet) ++ explored)
      
    }
    
  }
    
//    println("--------------------")
//    initial.toList foreach println
//    println("--------------------")
    
//    if (initial.isEmpty) Stream.empty
//    else {
//      val headBlock = initial.head._1
//      val headMoves = initial.head._2
//	  val next = newNeighborsOnly(neighborsWithHistory(headBlock, headMoves), explored)
//      val result = initial ++ from(next, (next.map { case (b, mvs) => b } toSet) ++ explored)
//      
//      result
//    }
    
//    if (initial.isEmpty) Stream.empty
//    else {
//	  val next = newNeighborsOnly(initial, explored)
//      val result = initial ++ from(next, (next.map { case (b, mvs) => b } toSet) ++ explored)
//      
//      result
//    }
    
    
//    if (initial.isEmpty) Stream.empty
//    else {
//      val nextMoves = newNeighborsOnly(neighborsWithHistory(initial.head._1, initial.head._2), Set(initial.head._1) ++ explored)
//      
//      initial ++ from(nextMoves, (nextMoves.toList.map { case (b, _) => b } toSet) ++ explored)
//    }
    
//    if (initial.isEmpty) Stream.empty
//    else {
//      
//      val more = initial.flatMap { case (b, mvs) => newNeighborsOnly(neighborsWithHistory(b, mvs), explored) }
//      
//      println("--------------------")
//      more.toList foreach println
//      println("--------------------")
//      
//      initial ++ from(more, (more.toList.map { case (b, _) => b } toSet) ++ explored)
//      
//    }
    
//    if (initial.isEmpty) Stream.empty
//    else {
//      
//      val more = for {
//        move <- initial
//        next <- newNeighborsOnly(neighborsWithHistory(move._1, move._2), explored)
//      } yield next
//      
//      println("--------------------")
//      more.toList foreach println
//      println("--------------------")
//      
//      initial ++ from(more, (more.toList.map { case (b, _) => b } toSet) ++ explored)
//      
//    }
//    
//  }
            
    

  /**
   * The stream of all paths that begin at the starting block.
   */
  lazy val pathsFromStart: Stream[(Block, List[Move])] = {

    val startSet = Set(startBlock)
    val allMovesFromStart = neighborsWithHistory(startBlock, Nil)
    val legalMovesFromStart = newNeighborsOnly(allMovesFromStart, startSet)
    
    from(legalMovesFromStart, startSet)
    
  }



  
  /**
   * Returns a stream of all possible pairs of the goal block along
   * with the history how it was reached.
   */
  lazy val pathsToGoal: Stream[(Block, List[Move])] = {
    
    println(pathsFromStart toList)
    
    pathsFromStart filter { case (b, mvs) => b == goal }
  }

  /**
   * The (or one of the) shortest sequence(s) of moves to reach the
   * goal. If the goal cannot be reached, the empty list is returned.
   *
   * Note: the `head` element of the returned list should represent
   * the first move that the player should perform from the starting
   * position.
   */
  lazy val solution: List[Move] = {
    if (pathsToGoal.isEmpty) Nil
    else (pathsToGoal map { case (b, mvs) => mvs }).head.reverse
  }
}
