package recfun


object play {

  def nth(n: Int, l: List[Int]): Int =
      if (n == 0) l.head
      else nth(n-1, l.tail)                       //> nth: (n: Int, l: List[Int])Int
      
  def length(l: List[Int]): Int =
      if (l.isEmpty) 0
      else 1 + length(l.tail)                     //> length: (l: List[Int])Int
      
  length(List(10, 11, 12, 13, 14, 15))            //> res0: Int = 6
      
}