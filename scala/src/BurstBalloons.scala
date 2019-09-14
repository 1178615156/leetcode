


object BurstBalloons {

  import scala.collection.immutable.TreeSet

  case class Value(index: Int, value: Int)

  implicit val OrderingValue: Ordering[Value] = Ordering.by(e => e.index)


  def maxCoins(nums: Array[Int]): Int = {
    nums.drop(1)
    var cache = Map.empty[IndexedSeq[Int], Int]

    def impl(seqs: IndexedSeq[Int]): Int = {
      if(cache.contains(seqs)) cache(seqs)
      else if(seqs.isEmpty) 1
      else if(seqs.length == 1) seqs.head
      else {

        var i = 0
        var result = Int.MinValue
        while (i < seqs.length) {
          val a = seqs.applyOrElse[Int, Int](i - 1, e => 1)
          val b = seqs.apply(i)
          val c = seqs.applyOrElse[Int, Int](i + 1, e => 1)
          val value = a * b * c

          result = math.max(result, value + impl(seqs))
          i += 1
        }
        cache += ((seqs, result))
        result
      }
    }

    impl(nums)
    //    var cache = Map.empty[TreeSet[Value], Int]
    //
    //    def impl(values: TreeSet[Value]): Int = {
    //      if(values.isEmpty) 0
    //      else if(values.size <= 1) values.map(_.value).product
    //      else if(cache.contains(values)) cache(values)
    //      else {
    //        val result = values.map(e => {
    //          val inc = values.until(e).lastOption.map(_.value).getOrElse(1) * e.value * values.from(Value(e.index + 1, 0)).headOption.map(_.value).getOrElse(1)
    //          impl(values - e) + inc
    //        }).max
    //        cache += ((values, result))
    //        result
    //      }
    //    }
    //
    //    val values = TreeSet(nums.filter(_ > 0).zipWithIndex.map { case (v, i) => Value(i, v) }.toList: _*)
    //    val result = impl(values)

    //    result
  }

  def main(args: Array[String]): Unit = {
    println(maxCoins(Array(3, 1, 5, 8)))

  }
}
