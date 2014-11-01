package forcomp

import Anagrams._

object play {

  val s = List("I", "love", "you")                //> s  : List[String] = List(I, love, you)
  val words = wordsFromSentence(s)                //> words  : forcomp.Anagrams.Sentence = List(Eli, Ely, evil, Io, ivy, Leo, Lev,
                                                  //|  Levi, levy, lie, lieu, live, Lou, love, Loy, oil, oily, olive, veil, vie, v
                                                  //| ile, you)
  val reduced = subtract(sentenceOccurrences(s), wordOccurrences(words(0)))
                                                  //> reduced  : forcomp.Anagrams.Occurrences = List((o,2), (u,1), (v,1), (y,1))
  
  reduced foreach println                         //> (o,2)
                                                  //| (u,1)
                                                  //| (v,1)
                                                  //| (y,1)
  

}