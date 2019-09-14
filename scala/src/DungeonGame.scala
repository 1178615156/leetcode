

object DungeonGame {
  def calculateMinimumHP(dungeon: Array[Array[Int]]): Int = {
    val rowNum = dungeon.length
    val columnNum = dungeon.head.length
    var cache = Map.empty[(Int, Int), Int]

    def impl(r: Int, c: Int): Int = {
      if(r == rowNum - 1 && c == columnNum - 1)
        math.min(0,dungeon(r)(c))
      else if(r == rowNum || c == columnNum)
        Int.MinValue
      else if(cache.contains((r, c))) {
        cache((r, c))
      } else {
        val value = dungeon(r)(c)
        val minNext = math.max(
          impl(r + 1, c),
          impl(r, c + 1)
        )
        val result = if(value >= 0 && minNext >= 0) 0
        else if(value >= 0 && minNext < 0)
          math.min(value + minNext, 0)
        else if(value < 0 && minNext >= 0)
          value
        else value + minNext

        cache += (((r, c), result))

        result

      }
    }

    val r = impl(0, 0)
//    cache.toList.sorted.foreach(println)
    if(r >= 0) 0
    else
      1 - r
  }

  def main(args: Array[String]): Unit = {
    println(calculateMinimumHP(Array(
      Array(-2, -3, 3),
      Array(-5, -10, 1),
      Array(10, 30, -5)
    )))
  }
}
