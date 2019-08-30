package leetcode

object MinimumWindowSubstring {

  case class Point(start: Int, end: Int) {
    val length = end - start
  }


  def minWindow(s: String, t: String): String = {
    val tCount = mkCount(t)
    var sCount = Map.empty[Char, Int]

    var i = 0
    var j = 0

    while (i < s.length && j < t.length) {
      val char = s.charAt(i)
      if(sCount.getOrElse(char, 0) < tCount.getOrElse(char, 0))
        j += 1
      sCount = sCount.updated(char, sCount.getOrElse(char, 0) + 1)
      i += 1
    }
    if(j < t.length) {
      ""
    } else {


      var starIndex = 0
      var endIndex = i

      var points = List(Point(starIndex, endIndex))

      while (true) {
        val startChar = s.charAt(starIndex)

        if((tCount.getOrElse(startChar, 0) == 0) || sCount.getOrElse(startChar, 0) > tCount.getOrElse(startChar, 0)) {
          sCount = sCount.updated(startChar, sCount(startChar) - 1)
          starIndex += 1
          points = Point(starIndex, endIndex) :: points
        } else if(endIndex < s.length) {
          val endChar = s.charAt(endIndex)
          sCount = sCount.updated(endChar, sCount.getOrElse(endChar, 0) + 1)
          endIndex += 1
        } else {
          val p = points.minBy(_.length)
          return s.substring(p.start, p.end)
        }
      }
      ""
    }

    //    def impl(chars: Seq[Char]): Seq[Char] = {
    //      if(chars.isEmpty) {
    //        Nil
    //      } else {
    //        val char = chars.head
    //        if(tCount.getOrElse(char, 0) == 0)
    //          impl(chars.tail)
    //        else if(sCount.getOrElse(char, 0) > tCount.getOrElse(char, 0)) {
    //          sCount = sCount.updated(char, sCount(char) - 1)
    //          impl(chars.tail)
    //        } else {
    //          chars
    //        }
    //      }
    //    }
    //
    //    if(tCount.forall { case (char, e) => sCount.getOrElse(char, 0) >= e }) {
    //      sCount = mkCount(s)
    //      val a = impl(impl(s.toList).reverse).reverse.mkString("")
    //      sCount = mkCount(s)
    //      val b = impl(impl(s.toList.reverse).reverse).mkString("")
    //      if(a.length <= b.length) a else b
    //    }
    //    else
    //      ""
  }

  def mkCount(s: Seq[Char]): Map[Char, Int] = {
    s.groupBy(e => e).mapValues(e => e.length).toMap
  }

  def main(args: Array[String]): Unit = {
    println(minWindow(
      "cabwefgewcwaefgcf",
      "cae"
    ))
  }
}