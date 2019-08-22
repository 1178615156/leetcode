package leetcode

object ThreeSum {

  import collection.Searching._

  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val (lessList, zeroList, moreList) = nums.foldLeft((Seq.empty[Int], Seq.empty[Int], Seq.empty[Int])) {
      case ((less, zero, more), value) =>
        if(value < 0)
          (-value +: less, zero, more)
        else if(value > 0)
          (less, zero, value +: more)
        else
          (less, value +: zero, more)
    }
    val sumWithZero =
      if(zeroList.isEmpty) Nil
      else if(zeroList.size < 3) (lessList.toSet intersect moreList.toSet).toList.map(e => List(-e, 0, e))
      else List(0, 0, 0) +: (lessList.toSet intersect moreList.toSet).toList.map(e => List(-e, 0, e))

    val lessSortedList = lessList.sorted
    val moreSortedList = moreList.sorted
    val result = List.newBuilder[List[Int]]
    result ++= sumWithZero
    result ++= twoSum(moreSortedList, lessSortedList)
    result ++= twoSum(lessSortedList, moreSortedList).map(_.map(-_))
    result.result()
  }

  def twoSum(left: Seq[Int], right: Seq[Int]): Seq[List[Int]] = {
    val size = right.groupBy(e => e).mapValues(_.size)
    left.flatMap { value =>
      right.view
        .takeWhile(_ < value)
        .filter(e => right.search(value - e) match {
          case Found(_) =>
            if(e != value - e) true
            else if(size(e) > 1) true
            else false
          case _        => false
        })
        .map(e => List(value, -e, -value + e))
        .force
    }.map(_.sorted).distinct
  }

  def main(args: Array[String]): Unit = {
    println(threeSum(Array(-1, 0, 1, 2, -1, -4)))
    println(threeSum(Array(3,0,-2,-1,1,2)))
    println(threeSum(Array(-1,0,1,2,-1,-4)))
  }
}
