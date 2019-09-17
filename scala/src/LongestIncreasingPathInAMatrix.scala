


object LongestIncreasingPathInAMatrix {

  case class Point(row: Int, column: Int)

  def longestIncreasingPath(matrix: Array[Array[Int]]): Int = {
    if(matrix.isEmpty || matrix.head.isEmpty)
      0
    else {

      val rowNum = matrix.length
      val columnNum = matrix.head.length
      val length = Array.fill[Int](rowNum, columnNum)(0)

      val maxValue = matrix.flatten.max

      def impl(point: Point): Int = {
        val i = point.row
        val j = point.column
        val value = matrix(i)(j)
        if(value == maxValue)
          1
        else if(length(i)(j) > 0)
          length(i)(j)
        else {

          val up = Point(i, j - 1)
          val down = Point(i, j + 1)
          val left = Point(i - 1, j)
          val right = Point(i + 1, j)

          val neighbor = Seq(up, down, left, right)
            .filter(e => e.row < rowNum && e.column < columnNum)
            .filter(e => e.row > -1 && e.column > -1)
            .filter(e => matrix(e.row)(e.column) > value)

          val path = if (neighbor.isEmpty)
            1
          else
            neighbor.map(e => impl(e)).max + 1

          length(i)(j) = path
          path
        }
      }


      for {
        i <- 0 until rowNum
        j <- 0 until columnNum
      } {
        impl(Point(i, j))
      }
      math.max(length.flatten.max,1)
    }

  }

}
