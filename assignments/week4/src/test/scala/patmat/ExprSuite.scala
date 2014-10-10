package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import exercises._

@RunWith(classOf[JUnitRunner])
class ExprSuite extends FunSuite {
  test("eval - Number") {
    val e: Expr = Number(3)
    assert(e.eval === 3)
  }
  test("eval - Sum of Number and Number") {
    val e: Expr = Sum(Number(3), Number(6))
    assert(e.eval === 9)
  }
  test("eval - Sum of Sum and Number") {
    val e: Expr = Sum(Sum(Number(3), Number(5)), Number(6))
    assert(e.eval === 14)
  }
  test("eval - Sum of Sum and Sum") {
    val e: Expr = Sum(Sum(Number(3), Number(5)), Sum(Number(6), Number(12)))
    assert(e.eval === 26)
  }
  test("eval - Prod of Number and Number") {
    val e: Expr = Prod(Number(7), Number(2))
    assert(e.eval === 14)
  }
  test("eval - Prod of Number and Sum") {
    val e: Expr = Prod(Number(7), Sum(Number(2), Number(3)))
    assert(e.eval === 35)
  }
  test("eval - Sum of Number and Prod") {
    val e: Expr = Sum(Number(7), Prod(Number(2), Number(3)))
    assert(e.eval === 13)
  }

  test("show - Number") {
    val e: Expr = Number(32)
    assert(e.show === "32")
  }
  test("show - Sum of Number and Number") {
    val e: Expr = Sum(Number(32), Number(2))
    assert(e.show === "32 + 2")
  }
  test("show - Sum of Number and Sum") {
    val e: Expr = Sum(Number(32), Sum(Number(15), Number(35)))
    assert(e.show === "32 + 15 + 35")
  }
  test("show - Sum of Sum and Sum") {
    val e: Expr = Sum(Sum(Number(87), Number(6)), Sum(Number(22), Number(357)))
    assert(e.show === "87 + 6 + 22 + 357")
  }
  test("show - Prod of Number and Number") {
    val e: Expr = Prod(Number(1), Number(5))
    assert(e.show === "1 * 5")
  }
  test("show - Prod of Sum and Number") {
    val e: Expr = Prod(Sum(Number(21), Number(43)), Number(85))
    assert(e.show === "(21 + 43) * 85")
  }
  test("show - Prod of Sum and Sum") {
    val e: Expr = Prod(Sum(Number(21), Number(43)), Sum(Number(77), Number(32)))
    assert(e.show === "(21 + 43) * (77 + 32)")
  }
  test("show - Sum of Prod and Prod") {
    val e: Expr = Sum(Prod(Number(21), Number(43)), Prod(Number(77), Number(32)))
    assert(e.show === "21 * 43 + 77 * 32")
  }
  test("show - Sum of Prod and Sum") {
    val e: Expr = Sum(Prod(Number(21), Number(43)), Sum(Number(77), Number(32)))
    assert(e.show === "21 * 43 + 77 + 32")
  }
  test("show - Sum of Sum and Prod") {
    val e: Expr = Sum(Sum(Number(21), Number(43)), Prod(Number(77), Number(32)))
    assert(e.show === "21 + 43 + 77 * 32")
  }
}
