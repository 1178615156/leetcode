

/*
 * @lc app=leetcode id=239 lang=scala
 *
 * [239] Sliding Window Maximum
 *
 * https://leetcode.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (37.14%)
 * Total Accepted:    139.7K
 * Total Submissions: 375.9K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7] 
 * Explanation: 
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty
 * array.
 * 
 * Follow up:
 * Could you solve it in linear time?
 */

object findMaxValue {

  def findMaxValue(nums: Array[Int], start: Int, end: Int) = {
    var (maxValue, maxCount) = (nums(start),1)
    var i = start + 1
    while (i < end){
      val value = nums(i)
      if (value >maxValue){
        maxValue = value
        maxCount = 1
      }else if (value == maxValue){
        maxCount +=1
      }
      i +=1
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

}

