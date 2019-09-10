object maxUncrossedLines {
  def maxUncrossedLines(A: Array[Int], B: Array[Int]): Int = {
    var cache = Map.empty[(Seq[Int], Seq[Int]), Int]

    def impl(a: Seq[Int], b: Seq[Int]): Int = {
      if(a.isEmpty || b.isEmpty)
        0
      else if(cache.contains((a, b)))
        cache.apply((a, b))
      else {
        val result = b.dropWhile(_ != a.head) match {
          case Seq()     => math.max(
            impl(a.tail, b),
            impl(a.tail, Nil)
          )
          case _ +: tail => math.max(
            impl(a.tail, b),
            impl(a.tail, tail) + 1
          )
        }
        cache += (((a, b), result))
        result
      }
    }

    impl(A, B)
  }


  def main(args: Array[String]): Unit = {
    println(maxUncrossedLines(Array(1, 4, 2), Array(1, 2, 4)))//2
    println(maxUncrossedLines(Array(2, 5, 1, 2, 5), Array(10, 5, 2, 1, 5, 2)))//3
    println(maxUncrossedLines(Array(1, 3, 7, 1, 7, 5), Array(1, 9, 2, 5, 1)))//2
  }
}