package playpack

object play {
  def nth(n: Int, l: List[Int]): Int =
    if (n < 0) throw new NoSuchElementException("Negative Nth")
    else if (n == 0) l.head
    else nth(n - 1, l.tail)                       //> nth: (n: Int, l: playpack.List[Int])Int
    
   val three = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> three  : playpack.Cons[Int] = playpack.Cons@3eb69969
   
   val neg = nth(-1, three)                       //> java.util.NoSuchElementException: Negative Nth
                                                  //| 	at playpack.play$$anonfun$main$1.nth$1(playpack.play.scala:5)
                                                  //| 	at playpack.play$$anonfun$main$1.apply$mcV$sp(playpack.play.scala:11)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at playpack.play$.main(playpack.play.scala:3)
                                                  //| 	at playpack.play.main(playpack.play.scala)
   val first = nth(0, three)
   val second = nth(1, three)
   val third = nth(2, three)
   val fourth = nth(3, three)
}