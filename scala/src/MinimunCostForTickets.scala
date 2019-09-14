

object MinimunCostForTickets {
  def mincostTickets(days: Array[Int], costs: Array[Int]): Int = {
    val costsAvgs = List(costs(0), costs(1) / 7.0, costs(2) / 30.0).min

    def evaluation(list: List[Int]) = list.length * costsAvgs


    var min = Int.MaxValue
    var cache = Map.empty[Int, Int]

    def impl(list: List[Int]): Int = {
      if(list.isEmpty)
        0
      else if(cache.contains(list.length))
        cache(list.length)
      else if(evaluation(list) > min)
        min + 1
      else {
        val head = list.head
        val day1 = list.tail
        val day7 = day1.dropWhile(_ <= head + 6)
        val day30 = day7.dropWhile(_ <= head + 29)

        val result = List(
          costs(0) + impl(day1),
          costs(1) + impl(day7),
          costs(2) + impl(day30)
        ).min
        cache = cache + (list.length -> result)
        if(result < min) min = min
        result
      }
    }
    impl(days.toList)
  }

  def main(args: Array[String]): Unit = {
    //    println(mincostTickets(
    //      Array(1, 4, 6, 7, 8, 20),
    //      Array(2, 7, 15)
    //    ))

    //    println(mincostTickets(
    //      Array(6,8,9,18,20,21,23,25),
    //      Array(2,10,41)
    //    ))

    println(mincostTickets(
      Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31),
      Array(2, 7, 15)
    ))
  }
}
