

class MinimumPathSum {

  case class Index(row: Int, column: Int)

  var cache = Map.empty[Index, Int]

  def minPathSum(grid: Array[Array[Int]], index: Index, rowMax: Int, columnMax: Int): Int = {
    val row = index.row
    val column = index.column
    val value = grid(row)(column)
    val result =
      if(cache.contains(index))
        cache.apply(index)
      else if(row == rowMax && column == columnMax)
        value
      else if(row == rowMax && column < columnMax)
        value + minPathSum(grid, index.copy(column = column + 1), rowMax, columnMax)
      else if(row < rowMax && column == columnMax)
        value + minPathSum(grid, index.copy(row = row + 1), rowMax, columnMax)
      else math.min(
        value + minPathSum(grid, index.copy(row = row + 1), rowMax, columnMax),
        value + minPathSum(grid, index.copy(column = column + 1), rowMax, columnMax)
      )

    cache += index -> result
    result
  }

  def minPathSum(grid: Array[Array[Int]]): Int = {
    cache = Map.empty
    minPathSum(grid, Index(0, 0), grid.length, grid.head.length)
  }
}
