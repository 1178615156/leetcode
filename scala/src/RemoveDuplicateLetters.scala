

object RemoveDuplicateLetters {
  def removeDuplicateLetters(s: String): String = {
    s.foldRight(s.toList.reverse)((r, l) => impl(l, r)).mkString.reverse
//    s.foldLeft(List.empty[Char])((l, r) => impl(l, r)).mkString.reverse
  }

  def impl(heads: List[Char], char: Char, rt: List[Char] = Nil): List[Char] = {
    heads match {
      case Nil                          => char :: rt.reverse
      case a :: b :: other if b == char =>
        if(a < b)
          char :: rt.reverse ::: (a :: other)
        else
          rt.reverse ::: heads
      case a :: other                   => impl(other, char, a :: rt)
    }
  }

  def main(args: Array[String]): Unit = {
    println(removeDuplicateLetters("bcabc"))
  }
}
