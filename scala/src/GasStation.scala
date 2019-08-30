package leetcode

object GasStation {
//  def canCompleteCircuit(gas: Array[Int], cost: Array[Int]): Int = {
//    gas(0) = gas(0) - cost(cost.length - 1)
//    var i = 1
//    while (i < gas.length) {
//      gas(i) = gas(i) - cost(i - 1)
//      i += 1
//    }
//  }

  def maxSubArray(nums: Array[Int]): Int = {
    var max = nums(0)
    var min = 0
    var min_index = 0
    var i = 0
    var curr = 0
    while (i < nums.length) {
      curr += nums(i)
      max = math.max(max, curr - min)
      if(curr < min) {
        min = curr
        min_index = i
      }
      i += 1
    }

    min_index
  }
}
