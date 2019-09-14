

object WordBreak {
  var cache = Map.empty[List[Char], Boolean]

  def wordBreak(s: List[Char], wordDict: List[String]): Boolean = {
    if(cache.contains(s))
      cache(s)
    else {
      val result = if(s.isEmpty)
        true
      else
        wordDict.exists { world =>
          s.startsWith(world) && wordBreak(s.drop(world.length), wordDict)
        }
      cache += ((s, result))
      result
    }
  }

  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    cache = Map.empty[List[Char], Boolean]
    if((s.toSet diff wordDict.flatten.toSet).nonEmpty)
      false
    else
      wordBreak(s.toList, wordDict.sortBy(_.length).reverse)
  }

  def main(args: Array[String]): Unit = {
    //    val s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    //    var word = List("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")

    val s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    var word = List("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba")

    wordBreak(s, word)
  }
}
