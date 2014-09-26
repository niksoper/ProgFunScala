package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("union can be chained") {
    new TestSets {
      val oneToSix = union(union(union(union(union(s1, s2), s3), s4), s5), s6)
      assert(contains(oneToSix, 1), "Union 1")
      assert(contains(oneToSix, 2), "Union 2")
      assert(contains(oneToSix, 3), "Union 3")
      assert(contains(oneToSix, 4), "Union 4")
      assert(contains(oneToSix, 5), "Union 5")
      assert(contains(oneToSix, 6), "Union 6")
    }
  }
  
  test("rangeSet includes numbers in range") {
    
    val upToTen = rangeSet(0, 10)
    
    assert(!contains(upToTen, -1), "Range -1")
    assert(!contains(upToTen, 11), "Range 11")
    
    assert(contains(upToTen, 0), "Range 0")
    assert(contains(upToTen, 1), "Range 1")
    assert(contains(upToTen, 2), "Range 2")
    assert(contains(upToTen, 3), "Range 3")
    assert(contains(upToTen, 4), "Range 4")
    assert(contains(upToTen, 5), "Range 5")
    assert(contains(upToTen, 6), "Range 6")
    assert(contains(upToTen, 7), "Range 7")
    assert(contains(upToTen, 8), "Range 8")
    assert(contains(upToTen, 9), "Range 9")
    assert(contains(upToTen, 10), "Range 10")
    
  }
  
  test("intersect contains shared elements of sets") {
    
    val threeToFive = intersect(rangeSet(1, 5), rangeSet(3, 10))
    
    assert(!contains(threeToFive, 0))
    assert(!contains(threeToFive, 2))
    assert(!contains(threeToFive, 6))
    assert(!contains(threeToFive, 11))
    
    assert(contains(threeToFive, 3))
    assert(contains(threeToFive, 4))
    assert(contains(threeToFive, 5))
    
  }
  
  test("diff contains elements of first set not in second set") {
    
    val oneToTwo = diff(rangeSet(1, 5), rangeSet(3, 10))
    
    assert(!contains(oneToTwo, 0))
    assert(!contains(oneToTwo, 3))
    assert(!contains(oneToTwo, 4))
    assert(!contains(oneToTwo, 5))
    assert(!contains(oneToTwo, 6))
    assert(!contains(oneToTwo, 7))
    assert(!contains(oneToTwo, 8))
    assert(!contains(oneToTwo, 9))
    assert(!contains(oneToTwo, 10))
    assert(!contains(oneToTwo, 11))
    
    assert(contains(oneToTwo, 1))
    assert(contains(oneToTwo, 2))
    
  }
  
  test("filter contains all elements in first set that satisfy predicate") {
    
    val twoAndFour = filter(rangeSet(1, 5), x => x % 2 == 0)
    
    assert(!contains(twoAndFour, 0))
    assert(!contains(twoAndFour, 1))
    assert(!contains(twoAndFour, 3))
    assert(!contains(twoAndFour, 5))
    assert(!contains(twoAndFour, 6))
    
    assert(contains(twoAndFour, 2))
    assert(contains(twoAndFour, 4))
    
  }
  
}
