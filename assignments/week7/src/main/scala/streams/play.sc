package streams

import streams._

object play {
  
  val v: Vector[Vector[Char]] = Vector(Vector('a', 'b', 'c'), Vector('d', 'e', 'f'))
                                                  //> v  : Vector[Vector[Char]] = Vector(Vector(a, b, c), Vector(d, e, f))
  v.indexWhere(_.indexOf('d') != -1)              //> res0: Int = 1
  
 // val v = Vector(1,2,3)
  //v.indexWhere(_ % 2 == 0)
  
}