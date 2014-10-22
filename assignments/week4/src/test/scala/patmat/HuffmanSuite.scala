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
    val threeLeaves = List(Leaf('e',1), Leaf('t',2), Leaf('x',3))
    val leaf = Leaf('x', 5)
  }

  test("until - t1") {    
    new TestTrees {
      val result = until(singleton, combine)(List(t1))
      assert(result === List(t1))
    }    
  }

  test("until - t2") {    
    new TestTrees {
      val result = until(singleton, combine)(List(t2))
      assert(result === List(t2))
    }    
  }

  test("until - threeLeaves") {    
    new TestTrees {
      val result = until(singleton, combine)(threeLeaves)
      assert(result === List(Fork(Fork(Leaf('e',1), Leaf('t',2), List('e', 't'), 3), Leaf('x',3), List('e', 't', 'x'), 6)))
    }    
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

  test("combine retains weight order") {
    val leaflist = List(Leaf('e', 12), Leaf('t', 13), Leaf('x', 14))
    assert(combine(leaflist) === List(Leaf('x',14), Fork(Leaf('e',12),Leaf('t',13),List('e', 't'),25)))
  }

  test("combine of single item list") {
    assert(combine(List(Leaf('s', 4))) === List(Leaf('s', 4)))
  }

  test("combine of two item list") {
    assert(combine(List(Leaf('r', 9), Leaf('y', 8))) === List(Fork(Leaf('r', 9), Leaf('y', 8), List('r', 'y'), 17)))
  }
  
  test("combine of empty list") {
    assert(combine(Nil) === Nil)
  }

  test("createCodeTree - abbccc") {
    val codeTree = createCodeTree("abbccc".toList)
    assert(codeTree === Fork(Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3), Leaf('c', 3), List('a', 'b', 'c'), 6))
  }
  
  test("createCodeTree - empty throws exception") {
    intercept[Error]{
      createCodeTree(Nil)
    }
  }
  
  test("decode a leaf") {
    new TestTrees {
      assert(decode(leaf, List(0)) === List('x'))
      assert(decode(leaf, List(1)) === List('x'))
    }
  }
  
  test("decode a left fork") {
    new TestTrees {
      assert(decode(t1, List(0)) === List('a'))
    }
  }

  test("decode a right fork") {
    new TestTrees {
      assert(decode(t1, List(1)) === List('b'))
    }
  }
  
  test("decode a left and right of a fork") {
    new TestTrees {
      assert(decode(t1, List(0, 1)) === List('a', 'b'))
    }
  }
  
  test("decode a tree with more than one level") {
    new TestTrees {
      assert(decode(t2, List(0, 1)) === List('b'))
      assert(decode(t2, List(1, 0, 0, 0, 1)) === List('d', 'a', 'b'))
    }
  }
  
  test("decode empty list") {
    new TestTrees {
      assert(decode(t1, Nil) === Nil)
      assert(decode(t2, Nil) === Nil)
      assert(decode(leaf, Nil) === Nil)
    }
  }
  
  test("encode empty list") {
    new TestTrees {
      assert(encode(t2)(Nil) === Nil)
    }
  }
  
  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("quickEncode empty list") {
    new TestTrees {
      assert(quickEncode(t2)(Nil) === Nil)
    }
  }
  
  test("quickEncode secret") {
    assert(quickEncode(frenchCode)("huffmanestcool".toList) === secret)
  }
  
  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("codeBits char not in table") {
    val table = List(('a', List(0)), ('b', List(1)))
    assert(codeBits(table)('c') === Nil)
  }
  
  test("codeBits char in table") {
    val table = List(('a', List(0)), ('b', List(1)))
    assert(codeBits(table)('b') === List(1))
  }

  test("convert t1") {
    new TestTrees {
      assert(convert(t1) === List(('a', List(0)), ('b', List(1))))
    }
  }
  
  test("convert t2") {
    new TestTrees {
      assert(convert(t2) === List(('a', List(0,0)), ('b', List(0,1)), ('d', List(1))))
    }
  }
}
