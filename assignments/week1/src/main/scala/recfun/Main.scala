package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    
    //countChange(0, List(1, 2))
    
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if (c < 0 || r < 0 || c > r) 0
    else if (c == 0 || c == r) 1 
    else pascal(c-1, r-1) + pascal(c, r-1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    
      def updateCount(count: Int, c: Char): Int =
        if (c == '(') count + 1
        else if (c == ')') count - 1
        else count
      
      def checkBalance(count: Int, remaining: List[Char]): Boolean =
        if (count < 0) false
        else if (remaining.isEmpty) count == 0
        else checkBalance(updateCount(count, remaining.head), remaining.tail)
        
      checkBalance(0, chars)
      
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    println("F(" + money + ", " + coins + ")")
    
    if (money == 0) {
      // nothing to give so no coins is the only option, +1
      1 
    }
    else if (money < 0 || coins.isEmpty) {
      // negative money or no coins and positive money, so not possible
      0 
    }
    else {
      // reduce the money by the first coin      
      // +
      // throw away the first coin
      
      countChange(money - coins.head, coins) + 
      countChange(money, coins.tail)
    }
  }
    
}
