package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  trait SimpleLevel extends SolutionChecker {
    
    val level = """|oSoooooT
                   |-oooo---
                   |-oo-----""".stripMargin
    
  }
  
  trait SuperSimpleLevel extends SolutionChecker {
    
    val level = """|oSoooooT""".stripMargin
    
  }
  
  trait Level3 extends SolutionChecker {
    /* terrain for level 3: passcode 918660 */
    val level = 
    """------ooooooo--
      |oooo--ooo--oo--
      |ooooooooo--oooo
      |oSoo-------ooTo
      |oooo-------oooo
      |------------ooo""".stripMargin
      
      val optsolution = List(Up, Right, Down, Down, Down, Right, Right, Right, Up, Up, Right, Down, Left, Up, Right, Right, Right, Up, Right)
  }
  
  trait LevelWithoutSolution extends SolutionChecker {
    val level =  """SoT""".stripMargin
  }

  test("level with no solution") {
    new LevelWithoutSolution {
      assert(solution.isEmpty)
    }
  }
  
  test("optimal solution for level 3") {
    new Level3 {
      assert(solve(solution) === Block(goal, goal))
    }
  }
  
  test("optimal solution length for level 3") {
    new Level3 {
      assert(solution.length === optsolution.length)
    }
  }
  
  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(terrain(Pos(0,1)), "0,1")
      assert(terrain(Pos(0,2)), "0,2")
      assert(!terrain(Pos(0,3)), "0,3")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }

  test("pathsFromStart - Level 1") {
    new Level1 {
      
      val expected = Set(
    		  				(Block(Pos(1, 2), Pos(1, 3)), List(Right)),
    		  				(Block(Pos(2, 1), Pos(3, 1)), List(Down))
    		  			 )	
      
      assert((pathsFromStart take 2).toSet === expected)
    		  			 
    }
  }
  
  test("pathsFromStart - Simple level take 2") {
    new SimpleLevel {
      
      val expected = Set(
    		  				(Block(Pos(0, 2), Pos(0, 3)), List(Right)),
    		  				(Block(Pos(1, 1), Pos(2, 1)), List(Down))
    		  			 )	
      
      assert((pathsFromStart take 2).toSet === expected)
    		  			 
    }
  }
  
  test("pathsFromStart - Simple level take 5") {
    new SimpleLevel {
      
      val expected = Set(
    		  				(Block(Pos(0, 2), Pos(0, 3)), List(Right)),
    		  				(Block(Pos(1, 1), Pos(2, 1)), List(Down)),
    		  				(Block(Pos(1, 2), Pos(1, 3)), List(Down,Right)),
    		  				(Block(Pos(0, 4), Pos(0, 4)), List(Right,Right)),
    		  				(Block(Pos(1, 2), Pos(2, 2)), List(Right,Down))
    		  			 )	
      
      assert((pathsFromStart take 5).toSet === expected)
    		  			 
    }
  }
  
  test("pathsFromStart - Simple level take 6") {
    new SimpleLevel {
      
      val expected = Set(
    		  				(Block(Pos(0, 2), Pos(0, 3)), List(Right)),
    		  				(Block(Pos(1, 1), Pos(2, 1)), List(Down)),
    		  				(Block(Pos(1, 2), Pos(1, 3)), List(Down,Right)),
    		  				(Block(Pos(0, 4), Pos(0, 4)), List(Right,Right)),
    		  				(Block(Pos(1, 2), Pos(2, 2)), List(Right,Down)),
    		  				(Block(Pos(0, 5), Pos(0, 6)), List(Right,Right,Right))
    		  			 )	
      
      assert((pathsFromStart take 6).toSet === expected)
    		  			 
    }
  }
  
  test("pathsFromStart - Super simple level") {
    new SuperSimpleLevel {
      //01234567
      //oSoooooT
      //__xx____ R
      //____x___ RR
      //_____xx_ RRR
      //_______x RRRR
        
      val expected = Set(
    		  				(Block(Pos(0, 2), Pos(0, 3)), List(Right)),
    		  				(Block(Pos(0, 4), Pos(0, 4)), List(Right,Right)),
    		  				(Block(Pos(0, 5), Pos(0, 6)), List(Right,Right,Right)),
    		  				(Block(Pos(0, 7), Pos(0, 7)), List(Right,Right,Right,Right))
    		  			 )	
      
      assert((pathsFromStart take 100).toSet === expected)
      
    }
  }
  
  test("newNeighborsOnly - empty if all positions explored") {
    new SuperSimpleLevel {
      //01234567
      //oSoooooT
      //__xx____ R
      //____x___ RR
      //_____xx_ RRR
      //_______x RRRR
        
      val neighbors = Set(
						    (Block(Pos(0,5),Pos(0,6)), List(Left,Right,Right,Right,Right))
						  ).toStream
      
      val explored = Set(
    		  				Block(Pos(0,1),Pos(0,1)), 
    		  				Block(Pos(0,2),Pos(0,3)), 
    		  				Block(Pos(0,4),Pos(0,4)),
    		  				Block(Pos(0,5),Pos(0,6))
    		  			)
    		  			
      assert(newNeighborsOnly(neighbors, explored) === Stream.empty)
      
    }
  }
  
  test("from - super simple third move") {
    new SuperSimpleLevel {
      //01234567
      //oSoooooT
      //__xx____ R
      //____x___ RR
      //_____xx_ RRR
      //_______x RRRR
        
      val initial = Stream((Block(Pos(0,4),Pos(0,4)), List(Right,Right)))
      
      val explored = Set(
    		  				Block(Pos(0,1),Pos(0,1)), 
    		  				Block(Pos(0,2),Pos(0,3)), 
    		  				Block(Pos(0,4),Pos(0,4))
    		  			)
    		  			
      assert(from(initial, explored).head === (Block(Pos(0,5),Pos(0,6)), List(Right,Right,Right)))
      
    }
  }
  
  
  test("pathsFromStart - super simple first move") {
    new SuperSimpleLevel {
      
      assert(pathsFromStart.head === (Block(Pos(0,2),Pos(0,3)), List(Right)))
      
    }
  }
  
  test("pathsFromStart - super simple second move") {
    new SuperSimpleLevel {
      
      assert(pathsFromStart.drop(1).head === (Block(Pos(0,4),Pos(0,4)), List(Right,Right)))
      
    }
  }
  
  test("pathsFromStart - super simple third move") {
    new SuperSimpleLevel {
      
      assert(pathsFromStart.drop(2).head === (Block(Pos(0,5),Pos(0,6)), List(Right,Right,Right)))
      
    }
  }
  
  test("pathsFromStart - super simple fourth move") {
    new SuperSimpleLevel {
      
      assert(pathsFromStart.drop(3).head === (Block(Pos(0,7),Pos(0,7)), List(Right,Right,Right,Right)))
      
    }
  }
  
  test("neighborsWithHistory") {
    new Level1 {
      val expected = Set(
						  (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
						  (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
						)
      
      assert(neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up)).toSet === expected)
    }
  }
  
  test("newNeighborsOnly") {
    new Level1 {
      
      val neighbors = Set(
						    (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
						    (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
						  ).toStream
      
      val explored = Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
						  
      val expected = Set(
						  (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
						).toStream
      
      assert(newNeighborsOnly(neighbors, explored) === expected)
      
    }
  }
  
  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
}
