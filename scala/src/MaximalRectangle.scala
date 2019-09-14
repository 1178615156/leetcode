

object MaximalRectangle {
  def maximalRectangle(matrix: Array[Array[Char]]): Int = {
    var binaryMatrix = matrix.map(e => BigInt(e.mkString(""), 2)).toIndexedSeq
    var max = 0

    while (binaryMatrix.nonEmpty) {
      max = math.max(max, findMaxRectangleInMatrix(binaryMatrix, 0, BigInt("1" * matrix.head.length, 2), 1))
      binaryMatrix = binaryMatrix.tail

    }
    max
  }


  def findMaxRectangleInMatrix(matrix: Seq[BigInt],
                               max: Int,
                               sum: BigInt,
                               i: Int): Int = {
    if(matrix.isEmpty)
      max
    else {
      val line = matrix.head
      val newSum = sum & line
      val newMax = math.max(max, rectangle(newSum) * i)
      findMaxRectangleInMatrix(matrix.tail, newMax, newSum, i+1)
    }

  }

  def rectangle(bigInt: BigInt):Int = {
    val x = bigInt.toString(2).split("0").map(_.length).toSeq
    if (x.isEmpty) 0 else x.max
  }

  def main(args: Array[String]): Unit = {
    println(maximalRectangle(Array(Array('0'))))
    println(maximalRectangle(Array(
      Array("1","0","1","0","0"),
      Array("1","0","1","1","1"),
      Array("1","1","1","1","1"),
      Array("1","0","0","1","0")
    ).map(_.map(_.head))))
  }
}
