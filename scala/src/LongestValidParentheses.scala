

object LongestValidParentheses {

  trait Parentheses {
    def index: Int

    def matchs(parentheses: Parentheses): Boolean = (this, parentheses) match {
      case (OpenParent(_), CloseParent(_)) => true
      case _                               => false
    }
  }

  case class OpenParent(index: Int) extends Parentheses

  case class CloseParent(index: Int) extends Parentheses

  def longestValidParentheses(s: String): Int = {
    val parents: Seq[Parentheses] = s.zipWithIndex.map {
      case ('(', index) => OpenParent(index)
      case (')', index) => CloseParent(index)
    }
    val parentMatchs = parserParentheses(Nil, parents, Nil)
    val parentIndex = parentMatchs.sorted
    sumSubString(parentIndex)

  }

  def sumSubString(seq: Seq[Int]): Int = {
    if (seq.isEmpty)
      0
    else{

    var max = 0
    var sum = 1
    var before = seq.head
    seq.tail.foreach { i =>
      if(i - before == 1) {
        sum += 1
      } else {
        sum = 1
      }
      before = i
    max  = math.max(max, sum)
    }
    max
  }
  }

  def parserParentheses(stack: Seq[Parentheses],
                        values: Seq[Parentheses],
                        result: Seq[Int]): Seq[Int] = {
    if(values.isEmpty) result
    else if(stack.isEmpty) parserParentheses(values.head +: stack, values.tail, result)
    else if(stack.head matchs values.head) parserParentheses(stack.tail, values.tail, stack.head.index +: values.head.index +: result)
    else parserParentheses(values.head +: stack, values.tail, result)
  }

  def main(args: Array[String]): Unit = {
    println(longestValidParentheses(")()())"))
  }
}
