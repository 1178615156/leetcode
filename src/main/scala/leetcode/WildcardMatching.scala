package leetcode

object WildcardMatching {
  val ASK  = '?'
  val STAR = '*'

  def isMatch(s: String, p: String): Boolean = {
    var cache = Map.empty[(Seq[Char], Seq[Char]), Boolean]

    def impl(s: Seq[Char], p: Seq[Char]): Boolean = {
      if(cache.contains(s, p)) cache.apply((s, p))
      else {

        val result = if(s.isEmpty) p.forall(_ == STAR)
        else if(s.nonEmpty && p.isEmpty) false
        else p match {
          case ASK +: other          => impl(s.tail, other)
          case STAR +: Nil           => true
          case STAR +: STAR +: other => impl(s, p.tail)
          case STAR +: ASK +: other  => impl(s.tail, STAR +: other)
          case STAR +: other         =>
            val prefix = other.takeWhile(e => e != STAR && e != ASK)

            val index = s.indexOfSlice(prefix)
            if(index < 0)
              false
            else {
              val sTail = s.drop(index)
              impl(sTail.drop(prefix.length), other.drop(prefix.length)) || (sTail.nonEmpty && impl(sTail.tail, p))
            }
          case any +: other          => any == s.head && impl(s.tail, other)
        }
        cache = cache + (((s, p), result))
        result
      }
    }

    impl(s.toSeq, p.toSeq)
  }


  def main(args: Array[String]): Unit = {
    //    println(isMatch("adceb", "*a*b"))
    println(isMatch("", ""))
    //    println(isMatch("aaaaaabbaabaaaaabababbabbaababbaabaababaaaaabaaaabaaaabababbbabbbbaabbababbbbababbaaababbbabbbaaaaaaabbaabbbbababbabbaaabababaaaabaaabaaabbbbbabaaabbbaabbbbbbbaabaaababaaaababbbbbaabaaabbabaabbaabbaaaaba"
    //      , "*bbb**a*******abb*b**a**bbbbaab*b*aaba*a*b**a*abb*aa****b*bb**abbbb*b**bbbabaa*b**ba**a**ba**b*a*a**aaa"))
  }
}
