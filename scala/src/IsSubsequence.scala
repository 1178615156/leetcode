

object IsSubsequence {

  def isSubsequence(s: String, t: String): Boolean = {
    val result = t.foldLeft(s.toSeq)((acc, c) => if(acc.nonEmpty && acc.head == c) acc.tail else acc)
    result.isEmpty
  }
}
