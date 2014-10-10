package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("times - abbccc") {
    val chars = List('a', 'b', 'b', 'c', 'c', 'c')
    assert(times(chars) === List(('a', 1), ('b', 2), ('c', 3)))
  }

  test("times - abbacccb") {
    val chars = List('a', 'b', 'b', 'a', 'c', 'c', 'c', 'b')
    assert(times(chars) === List(('a', 2), ('b', 3), ('c', 3)))
  }
  
  test("times - empty list") {
    val chars = List()
    assert(times(chars) === List())
  }
  
  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("makeOrderedLeafList for empty list") {
    assert(makeOrderedLeafList(Nil) === Nil)
  }

  test("singleton - empty") {
    assert(singleton(Nil) === false)
  }

  test("singleton - single") {
    assert(singleton(List(Leaf('x', 6))) === true)
  }

  test("singleton - multiple") {
    assert(singleton(List(Leaf('x', 6), Leaf('y', 8))) === false)
  }
  
  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of single item list") {
    assert(combine(List(Leaf('s', 4))) === List(Leaf('s', 4)))
  }

  test("combine of two item list") {
    assert(combine(List(Leaf('r', 9), Leaf('y', 8))) === List(Leaf('r', 9), Leaf('y', 8)))
  }
  
  test("combine of empty list") {
    assert(combine(Nil) === Nil)
  }
  
//  test("decode and encode a very short text should be identity") {
//    new TestTrees {
//      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
//    }
//  }
}
