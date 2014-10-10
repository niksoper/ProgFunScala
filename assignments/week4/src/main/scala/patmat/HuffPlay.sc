package patmat

object HuffPlay {
  val list = List(1,2,5,-1,2,7)                   //> list  : List[Int] = List(1, 2, 5, -1, 2, 7)
  val sorted = list.sortWith((a, b) => b < a)     //> sorted  : List[Int] = List(7, 5, 2, 2, 1, -1)
}