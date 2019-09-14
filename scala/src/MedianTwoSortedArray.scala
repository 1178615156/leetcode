

import scala.util.Try

object MedianTwoSortedArray {

  import scala.collection.Searching._

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    if(nums1.isEmpty)
      if(nums2.length % 2 == 1) nums2(nums2.length / 2)
      else (nums2(nums2.length / 2) + nums2(nums2.length / 2 - 1)) / 2.0
    else if(nums2.isEmpty) {
      if(nums1.length % 2 == 1) nums1(nums1.length / 2)
      else (nums1(nums1.length / 2) + nums1(nums1.length / 2 - 1)) / 2.0
    }
    else {

      val min: Int = math.min(nums1.head, nums2.head)
      val max: Int = math.max(nums1.last, nums2.last)

      val length = nums1.length + nums2.length

      var mid = (min + max) / 2
      while (true) {
        val x1 = nums1.search(mid).insertionPoint
        val x2 = nums2.search(mid).insertionPoint
        if(2 * (x1 + x2) == length - 1)
          return mid
        else if(2 * (x1 + x2) == length)
          return (mid + math.max(
            Try(nums1(x1 - 1)).getOrElse(nums1(x1)),
            Try(nums1(x2 - 1)).getOrElse(nums1(x2)))) / 2.0
        else if(2 * (x1 + x2) < length) mid = (mid + 1 + max) / 2
        else if(2 * (x1 + x2) > length) mid = (min + mid - 1) / 2

      }
      ???
    }
  }

  def main(args: Array[String]): Unit = {
    println(findMedianSortedArrays(Array(1, 3), Array(2)))
    println(findMedianSortedArrays(Array(1, 2), Array(3, 4)))
  }
}
