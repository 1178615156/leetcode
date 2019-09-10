object reorganizeString {
  def reorganizeString(S: String): String = {
    val charCount  = S.toList.groupBy(e => e)
      .mapValues(_.size)
      .toList
      .sortBy(e => -e._2)
    val count      = charCount.map(e => e._2)
    val maxCount   = count.head
    val maxChar    = charCount.head._1
    val otherCount = S.size - maxCount
    val otherS     = S.toSeq.filter(_ != maxChar).sorted
    if(otherCount >= maxCount - 1) {
      val result = Array.fill[StringBuilder](maxCount)(new StringBuilder(maxChar.toString))
      for (i <- 0 until otherS.length) {
        result(i % maxCount).append(otherS(i))
      }
      result.foldLeft(new StringBuilder())(_.append(_)).toString()
    } else {
      ""
    }
  }
}