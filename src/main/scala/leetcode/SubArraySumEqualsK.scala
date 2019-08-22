package leetcode

object SubArraySumEqualsK {
  def sumArray(nums: Array[Int]): Array[Int] = {
    var start = 1
    val end = nums.length
    var sum = nums(0)
    while (start < end) {
      sum += nums(start)
      nums(start) = sum
      start += 1
    }
    nums
  }

  def subarraySum(nums: Array[Int], k: Int): Int = {
    val sumNums = sumArray((Array.newBuilder[Int] ++= nums).result())
    val valueIndex = sumNums.zipWithIndex.toSeq.groupBy(_._1).mapValues(e => e.map(_._2))
    var i = 0
    val end = sumNums.length
    var result = 0
    while (i < end) {
      val value = sumNums(i) - nums(i)
      val expect = k + value
      result += valueIndex.getOrElse(expect, Nil).count(_ > i)
      if(nums(i) == k)
        result += 1
      i += 1
    }
    result
  }

  def main(args: Array[String]): Unit = {
    //    println(sumArray(Array(1, 2, 3, 4)).toList)
    println(subarraySum(Array(1, 2, 3), 3))
  }
}
