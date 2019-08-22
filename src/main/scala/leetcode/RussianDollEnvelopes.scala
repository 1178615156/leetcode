package leetcode

import scala.collection.immutable.TreeSet

object RussianDollEnvelopes {

  case class Point(x: Int, y: Int)



  implicit val PointOrdering: Ordering[Point] = Ordering.by(e => (e.y, e.x))

  def maxEnvelopes(envelopes: Array[Array[Int]]): Int = {
    val points = envelopes.map(e => Point(e(0), e(1))).sortBy(_.x)

    var map = Map.empty[Point, Int]
    points.indices foreach { i =>
      val point = points(i)
      val head = points.slice(0, i).filter(e => e.x < point.x && e.y < point.y).map(e => map(e))
      val result = if(head.isEmpty)
        1
      else
        head.max + 1
      map += (point -> result)

    }
    if (map.isEmpty)
      0
    else
      map.values.max
  }

  def main(args: Array[String]): Unit = {
    println(maxEnvelopes(Array(
      Array(4, 5),
      Array(4, 6),
      Array(6, 7),
      Array(2, 3),
      Array(1, 1)
    )))
  }
}
