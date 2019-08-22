package leetcode


object WordSearch {

  case class Point(row: Int, col: Int)


  def exist(board: Array[Array[Char]], word: String): Boolean = {
    val row_num = board.size
    val col_num = board.headOption.map(_.size).getOrElse(0)

    def neighboring(point: Point): Seq[Point] = {
      val up = Point(point.row - 1, point.col)
      val down = Point(point.row + 1, point.col)
      val left = Point(point.row, point.col - 1)
      val right = Point(point.row, point.col + 1)
      List(up, down, left, right).filter(e => e.row >= 0 && e.col >= 0).filter(e => e.row < row_num && e.col < col_num)
    }

    def impl(word: Seq[Char], points: Seq[Point]): Boolean = {
      val result = if(word.isEmpty) {
        true
      } else {
        val point = points.head

        val up = Point(point.row - 1, point.col)
        val down = Point(point.row + 1, point.col)
        val left = Point(point.row, point.col - 1)
        val right = Point(point.row, point.col + 1)

        val neighbs = neighboring(points.head)
        val next = neighbs.filter(e => !points.contains(e) && word.head == board(e.row)(e.col))
        next.exists(e => impl(word.tail, e +: points))
      }
      result
    }

    val start = for {
      row <- 0 until row_num
      col <- 0 until col_num
      if board(row)(col) == word.head
    } yield
      Point(row, col)
    start.exists(point => impl(word.tail, point :: Nil))
  }

  def findWords(board: Array[Array[Char]], words: Array[String]): List[String] = {
    words.filter(e => exist(board, e)).toList
  }
}
