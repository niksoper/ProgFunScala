package recfun


object play {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(125); 

  def nth(n: Int, l: List[Int]): Int =
      if (n == 0) l.head
      else nth(n-1, l.tail);System.out.println("""nth: (n: Int, l: List[Int])Int""");$skip(94); 
      
  def length(l: List[Int]): Int =
      if (l.isEmpty) 0
      else 1 + length(l.tail);System.out.println("""length: (l: List[Int])Int""");$skip(47); val res$0 = 
      
  length(List(10, 11, 12, 13, 14, 15));System.out.println("""res0: Int = """ + $show(res$0))}
      
}
