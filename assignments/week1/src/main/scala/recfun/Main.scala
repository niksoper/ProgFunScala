package recfun
import common._

object Main {
  def main(args: Array[String]) {
//    println("Pascal's Triangle")
//    for (row <- 0 to 10) {
//      for (col <- 0 to row)
//        print(pascal(col, row) + " ")
//      println()
//    }
    
    countChange(0, List(1, 2))
    
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
    if (c < 0 || r < 0) 0
    else if (c == 0 || c >= r) 1 
    else pascal(c, r-1) + pascal(c+1, r-1)

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

    def useCoin(money: Int, coin: Int): Int =
      if (coin <= money) money - coin
      else money
      
    def iterCountChange(remainingMoney: Int, remainingCoins: List[Int], count: Int): Int = {
      
      if (remainingMoney == 0 || remainingCoins.isEmpty) count
      else {
        
        def newMoney = useCoin(remainingMoney, remainingCoins.head)
      
	      if (remainingMoney > newMoney)
	        if (newMoney == 0) iterCountChange(money, remainingCoins.tail, count + 1)
	        else iterCountChange(newMoney, remainingCoins, count)
	      else iterCountChange(remainingMoney, remainingCoins.tail, count)
      }
      
      
    }
      
    iterCountChange(money, coins, 0)
    
    
  }
}
