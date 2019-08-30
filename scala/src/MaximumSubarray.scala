package leetcode

object MaximumSubarray {
  //  def maxSubArray(nums: Array[Int]): Int = {
  //    if (nums.count(_ >0) <=1)
  //      return nums.max
  //    var sum = 0
  //    var min = nums(0)
  //    var max = 2 * min
  //    var max_sum_array_sum = nums(0)
  //    nums.foreach{i=>
  //      sum +=i
  //      if (sum < min){
  //        max_sum_array_sum = math.max(max_sum_array_sum,max - min)
  //        min = sum
  //        max = min
  //      }else{
  //        max = math.max(max,sum)
  //        max_sum_array_sum = math.max(max_sum_array_sum,max - min)
  //      }
  //      println((sum,min,max,max_sum_array_sum))
  //    }
  //    max_sum_array_sum
  //  }
  def maxSubArray(nums: Array[Int]): Int = {
    var max = nums(0)
    var min = 0
    var i = 0
    var curr = 0
    while (i < nums.length) {
      curr += nums(i)
      max = math.max(max, curr - min)
      min = math.min(min, curr)
      i += 1
    }

    max
  }

  def main(args: Array[String]): Unit = {
    println(maxSubArray(Array(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
  }
}
