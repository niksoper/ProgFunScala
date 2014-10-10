package exercises

object play {
  val e: Expr = Prod(Sum(Number(3), Number(2)), Number(8))
                                                  //> e  : exercises.Expr = Prod(Sum(Number(3),Number(2)),Number(8))
  println(e.show)                                 //> (3 + 2) * 8
  e.eval                                          //> res0: Int = 40
}