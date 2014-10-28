package forcomp

object play {
  
  val ls = Seq(1,2,3,4,5)                         //> ls  : Seq[Int] = List(1, 2, 3, 4, 5)
  
  ls zip ls                                       //> res0: Seq[(Int, Int)] = List((1,1), (2,2), (3,3), (4,4), (5,5))
  
  val nest = List(ls, ls, ls)                     //> nest  : List[Seq[Int]] = List(List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 5), List
                                                  //| (1, 2, 3, 4, 5))
  nest.flatMap(x => x)                            //> res1: List[Int] = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
  nest.flatten                                    //> res2: List[Int] = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
  
  
  val occs = List(('a', 1), ('b', 1), ('c', 1))   //> occs  : List[(Char, Int)] = List((a,1), (b,1), (c,1))
  val occsNest = List(occs, occs, occs)           //> occsNest  : List[List[(Char, Int)]] = List(List((a,1), (b,1), (c,1)), List((
                                                  //| a,1), (b,1), (c,1)), List((a,1), (b,1), (c,1)))
           occsNest.flatten                       //> res3: List[(Char, Int)] = List((a,1), (b,1), (c,1), (a,1), (b,1), (c,1), (a,
                                                  //| 1), (b,1), (c,1))
           
 	
 	val result = for {
 		o <- occs
 		on <- occsNest
 	} yield (o._1, on.count(occ => occ._1 == o._1))
                                                  //> result  : List[(Char, Int)] = List((a,1), (a,1), (a,1), (b,1), (b,1), (b,1),
                                                  //|  (c,1), (c,1), (c,1))
   
 
 
 
}