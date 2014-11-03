package forcomp

import Anagrams._

object play {

  val s = List("I", "love", "you")                //> s  : List[String] = List(I, love, you)
  val sOccs = sentenceOccurrences(s)              //> sOccs  : forcomp.Anagrams.Occurrences = List((e,1), (i,1), (l,1), (o,2), (u,
                                                  //| 1), (v,1), (y,1))
  makeSentences("you", sOccs) foreach println     //> List(you, Io)
                                                  //| List(you, you)
                                                  //| List(you, Io)
                                                  //| List(you, vie)
                                                  //| List(you, you)
                                                  //| List(you, Loy)
                                                  //| List(you, Lou)
                                                  //| List(you, Io)
                                                  //| List(you, oily)
                                                  //| List(you, oil)
                                                  //| List(you, ivy)
                                                  //| List(you, you)
                                                  //| List(you, Io)
                                                  //| List(you, ivy)
                                                  //| List(you, ivy)
                                                  //| List(you, vie)
                                                  //| List(you, evil)
                                                  //| List(you, Levi)
                                                  //| List(you, live)
                                                  //| List(you, veil)
                                                  //| List(you, vile)
                                                  //| List(you, Eli)
                                                  //| List(you, lie)
                                                  //| List(you, vie)
                                                  //| List(you, lieu)
                                                  //| List(you, Lev)
                                                  //| List(you, Leo)
                                                  //| List(you, you)
                                                  //| List(you, Loy)
                                                  //| List(you, Ely)
                                                  //| List(you, Lou)
                                                  //| List(you, Leo)
                                                  //| List(you, Lou)
                                                  //| List(you, Io)
                                                  //| List(you, oil)
                                                  //| List(you, Leo)
                                                  //| List(you, love)
                                                  //| List(you, Lev)
                                                  //| List(you, Io)
                                                  //| List(you, Leo)
                                                  //| List(you, you)
                                                  //| List(you, Loy)
                                                  //| List(you, Ely)
                                                  //| List(you, Lou)
                                                  //| List(you, Io)
                                                  //| List(you, Eli)
                                                  //| List(you, lie)
                                                  //| List(you, oily)
                                                  //| List(you, lieu)
                                                  //| List(you, oil
                                                  //| Output exceeds cutoff limit.
  
  
  

}