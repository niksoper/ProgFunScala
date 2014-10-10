package exercises

trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    case Prod(l, r) => l.eval * r.eval
  }
  
  def show: String = this match {
    case Number(n) => n.toString
    case Sum(e1: Expr, e2: Expr) => e1.show + " + " + e2.show
    case Var(name) => name
    case Prod(left: Sum, right:Sum) => "(" + left.show + ") * (" + right.show + ")"
    case Prod(left: Sum, right:Expr) => "(" + left.show + ") * " + right.show
    case Prod(left: Expr, right:Sum) => left.show + " * (" + right.show + ")"
    case Prod(left, right) => left.show + " * " + right.show
  }
  
}

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Var(name: String) extends Expr
