/*
 * @lc app=leetcode id=452 lang=scala
 *
 * [452] Minimum Number of Arrows to Burst Balloons
 */
object Solution {

  case class Point(start: Int, end: Int) {
    def before(x: Int) = (start <= x && x <= end) 
  }

  def findMinArrowShots(points: Array[Array[Int]]): Int = {
    if(points.isEmpty)
      0
    else {

      var values = points.map(e => Point(e(0), e(1))).sortBy(e => e.end).toList
      var count = 1
      var x = values.head.end
      while (values.nonEmpty) {
        val point = values.head
        if(point.before(x)) {
          values = values.tail
        } else {
          x = point.end
          count += 1
        }
      }
      count
    }
  }


  def main(args: Array[String]): Unit = {
    println(findMinArrowShots(Array(
      Array(10, 16),
      Array(2, 8),
      Array(1, 6),
      Array(7, 12)
    )))
  }
}

