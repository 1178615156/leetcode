object findLongestChain {

  case class Pair(start: Int, end: Int)

  def findLongestChain(pairs: Array[Array[Int]]): Int = {
    var cache = Map.empty[Seq[Pair], Int]

    def impl(pairs: Seq[Pair]): Int = {
      if(cache.contains(pairs)) cache(pairs)
      else {

        val result = pairs match {
          case Seq()        => 0
          case head +: tail =>
            val headMax  = impl(tail.dropWhile(_.start <= head.end)) + 1
            val crossMax = impl(tail)
            math.max(crossMax, headMax)
        }
        cache += ((pairs, result))
        result
      }
    }

    val list = pairs.toSeq.map(e => Pair(e(0), e(1))).sortBy(_.start)
    impl(list)
  }



}