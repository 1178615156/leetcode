package leetcode

object MinimumSizeSubarraySum {
  def minSubArrayLen(s: Int, nums: Array[Int]): Int = {
    if(nums.length < 1)
      0
    else {

      var i = 0
      var j = 1
      var sum = nums(i)
      var min = Int.MaxValue
      while (j < nums.length || sum >= s) {
        if (sum >=s ){
          min = math.min(min, j - i)
        }
        if(sum < s) {
          sum += nums(j)
          j += 1
        }
        else {
          sum -= nums(i)
          i += 1
        }
      }
      if(min == Int.MaxValue) 0 else min
    }
  }

  def main(args: Array[String]): Unit = {
//    minSubArrayLen(4, Array(1,4,4))
    minSubArrayLen(7, Array(2, 3, 1, 2, 4, 3))
  }
}
