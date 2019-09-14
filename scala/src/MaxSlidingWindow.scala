

import scala.util.Try

object MaxSlidingWindow {

  def findMaxValue(nums: Array[Int], start: Int, end: Int) = {
    var (maxValue, maxCount) = (nums(start), 1)
    var i = start + 1
    while (i < end) {
      val value = nums(i)
      if(value > maxValue) {
        maxValue = value
        maxCount = 1
      } else if(value == maxValue) {
        maxCount += 1
      }
      i += 1
    }
    maxValue -> maxCount
  }

  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    if(nums.isEmpty)
      Array()
    else {
      var start = 0
      var end = start + k
      var (maxValue, maxCount) = findMaxValue(nums, start, end)
      val result = Array.newBuilder[Int]
      result += maxValue
      while (end < nums.length) {
        val out = nums(start)
        val in = nums(end)
        start += 1
        end += 1
        if(in > maxValue) {
          maxValue = in
          maxCount = 1
        } else if(in == maxValue) {
          maxCount += 1
        } else {

        }

        if(out == maxValue) {
          maxCount -= 1
          if(maxCount == 0) {
            val (a, b) = findMaxValue(nums, start, end)
            maxValue = a
            maxCount = b
          }
        }
        result += maxValue

      }
      result.result()
    }
  }

  def main(args: Array[String]): Unit = {
    val nums = Array(1, 3, -1, -3, 5, 3, 6, 7)
    maxSlidingWindow(nums, 3).foreach(println)
  }
}
