object topKFrequent {
  def topKFrequent(words: Array[String], k: Int): List[String] = {
    words.groupBy(e=>e).mapValues(e=>e.size).toList.sortBy(e=> (-e._2,e._1)).take(k).map(_._1)

  }
}