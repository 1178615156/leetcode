object scoreOfParentheses {

  trait Value

  case object Left extends Value

  case object Right extends Value

  case class Num(int: Int) extends Value

  def scoreOfParentheses(S: String): Int = {
    val list   = S.toList.map {
      case '(' => Left
      case ')' => Right
    }
    var result = impl(list)
    while (result.length > 1) {
      println(result)
      result = impl(result)
    }

    result.head.asInstanceOf[Num].int
  }

  def impl(list: List[Value]): List[Value] = list match {
    case Nil                                => Nil
    case _ :: Nil                           => list
    case Num(a) :: Num(b) :: other          => Num(a + b) :: impl(other)
    case Left :: Right :: other             => Num(1) :: impl(other)
    case Left :: Num(num) :: Right :: other => Num(2 * num) :: impl(other)
    case e :: other                         => e :: impl(other)
  }

  def main(args: Array[String]): Unit = {
    println(scoreOfParentheses("(()(()))"))
  }
}