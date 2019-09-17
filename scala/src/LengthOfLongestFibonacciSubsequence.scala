

object LengthOfLongestFibonacciSubsequence {
  def lenLongestFibSubseq(A: Array[Int]): Int = {
    import scala.collection.Searching._
    def count(aVal: Int, bVal: Int, rt: Int = 2): (Int, Int) = {
      A.search(aVal + bVal) match {
        case Found(foundIndex)     => count(bVal, aVal + bVal, rt + 1)
        case InsertionPoint(index) =>
          rt -> index
      }
    }

    val maxValue = A.last
    val mean = maxValue / 2 + 1
    val len = A.length
    var a = 0
    var maxLen = 0
    val aMaxPoint = A.search(mean).insertionPoint + 1
    while (a < aMaxPoint) {
      var b = a + 1
      val aVal = A(a)
      if(aVal * (maxLen - 1) > maxValue) a = len
      else {
        while (b < len) {
          val bVal = A(b)
          if(bVal + aVal > maxValue) b = len
          else if(aVal + bVal > mean && maxLen >= 3) b = len
          else if(bVal * (maxLen - 2) > maxValue) b = len

          else {
            val (size, endPoint) = count(aVal, bVal)
            maxLen = math.max(maxLen, size)
            if(endPoint >= len) {
              b = len
              a = len
            }
            else
              b += 1
          }

        }
        a += 1
      }
    }


    if(maxLen == 2) 0 else maxLen
  }


  def main(args: Array[String]): Unit = {
    println(lenLongestFibSubseq(Array(1, 2, 3, 4, 5, 6, 7, 8)))

    f"123"
  }
}
