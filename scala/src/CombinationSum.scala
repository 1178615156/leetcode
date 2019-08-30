package leetcode

import scala.annotation.meta.getter

//yujieshui
//2019 / 3 / 19

class CombinationSum {

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    import scala.collection.Searching._
    nums.search(target) match {
      case Found(foundIndex)              =>
        Array(foundIndex, nums.search(target + 1).insertionPoint)
      case InsertionPoint(insertionPoint) =>
        Array(-1, -1)
    }
  }


  def combinationSum(candidates: List[Int], target: Int): List[List[Int]] = {
    if(target == 0)
      List(List())
    else if(candidates.isEmpty || target < 0)
      Nil
    else {
      val head = candidates.head
      (0 to (target / head)).toList flatMap { e =>
        val nextTarget = target - e * head
        combinationSum(candidates.tail, nextTarget).map(l => Range(0, e).foldLeft(l)((l, r) => head +: l))
      }
    }
  }


}


