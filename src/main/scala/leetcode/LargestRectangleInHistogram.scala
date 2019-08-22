package leetcode

object LargestRectangleInHistogram {
  def largestRectangleArea(heights: Array[Int]): Int = {
    impl(heights.toList)
  }

  def impl(heights: List[Int]): Int = {
   if(isOrder(heights)) {
      val (max, _) = heights.foldLeft((0, heights.length)) {
        case ((max, len), e) =>
          (math.max(max, e * len), len - 1)
      }
      max
    }
    else {
      val min = heights.min
      val (l, r) = heights.splitAt(heights.indexOf(min))

      math.max(min * heights.length, math.max(impl(l), impl(r.tail)))
    }

  }

  def isOrder(l: List[Int]): Boolean = {
    l match {
      case Nil             => true
      case e :: Nil        => true
      case a :: b :: other =>
        if(a <= b)
          isOrder(l.tail)
        else
          false
    }
  }

  def main(args: Array[String]): Unit = {

  }
}
