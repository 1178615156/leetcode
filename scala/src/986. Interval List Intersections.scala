object intervalIntersection {

  case class Point(start: Int, end: Int) {
    def intersection(that: Point): Option[Point] = {
      if((this.start <= that.start && that.start <= this.end)
        || (that.start <= this.start && this.start <= that.end))
        Some(Point(math.max(this.start, that.start), math.min(this.end, that.end)))
      else
        None
    }
  }

  def impl(l1: Seq[Point], l2: Seq[Point], result: Seq[Point]): Seq[Point] = {
    if(l1.isEmpty || l2.isEmpty)
      result.reverse
    else {
      val a = l1.head
      val b = l2.head

      val newResult = a.intersection(b) match {
        case Some(e) => e +: result
        case _       => result
      }
      if(a.end < b.end)
        impl(l2, l1.tail, newResult)
      else
        impl(l1, l2.tail, newResult)
    }
  }

  def intervalIntersection(A: Array[Array[Int]], B: Array[Array[Int]]): Array[Array[Int]] = {


    impl(A.map(e => Point(e(0), e(1))), B.map(e => Point(e(0), e(1))), Nil).map(e => Array(e.start, e.end)).toArray

  }

  def main(args: Array[String]): Unit = {
    println(intervalIntersection(
      A = Array(Array(0, 2), Array(5, 10), Array(13, 23), Array(24, 25)),
      B = Array(Array(1, 5), Array(8, 12), Array(15, 24), Array(25, 26))
    ))
  }
}