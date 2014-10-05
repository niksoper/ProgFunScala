package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }
  
  trait BalancedTestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "d body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "a body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
    val megaSet = set5.incl(new Tweet("Alan", "Horse", 50))
    
    
    def nth(n: Int, tl: TweetList): Tweet =
      if (tl.isEmpty) throw new NoSuchElementException("nth of empty TweetList: [" + n + "]")
      else if (n < 0) throw new IndexOutOfBoundsException
      else if (n == 0) tl.head
      else nth(n - 1, tl.tail)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("filter: a on balanced set5") {
    new BalancedTestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on balanced set5") {
    new BalancedTestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new BalancedTestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }
  
  test("union: empty with empty") {
    new BalancedTestSets {
      assert(size(set1.union(new Empty)) === 0)
    }
  }

  test("union: with empty set (1)") {
    new BalancedTestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new BalancedTestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }
  
  test("mostRetweeted: set5") {
    new BalancedTestSets {
      val most = set5.mostRetweeted
      
      assert(most.retweets === 20)
      assert(most.user === "a")
      assert(most.text === "d body")
    }
  }
  
  test("mostRetweeted: megaSet") {
    new BalancedTestSets {
      val most = megaSet.mostRetweeted
      
      assert(most.retweets === 50)
      assert(most.user === "Alan")
      assert(most.text === "Horse")
    }
  }

  test("descending: megaSet") {
    new BalancedTestSets {
      val trends = megaSet.descendingByRetweet
      assert(!trends.isEmpty)
      assert(nth(0, trends).user === "Alan")
      assert(nth(1, trends).user === "a")
      assert(nth(2, trends).user === "b")
      assert(nth(3, trends).user === "d")
      assert(nth(4, trends).user === "c")
    }
  }
}
