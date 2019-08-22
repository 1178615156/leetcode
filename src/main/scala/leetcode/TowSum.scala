package leetcode

class TowSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val indexMap: Map[Int, Array[Int]] = nums.zipWithIndex.groupBy(_._1).mapValues(_.map(_._2))

    val a = nums.collectFirst {
      case i if 2 * i != target && indexMap.contains(target - i) =>
        Array(indexMap(i).head,indexMap(target-i).head)
      case i if 2 * i == target && (indexMap.getOrElse(i, Array()).length > 1) =>
        indexMap(i).take(2)
    }
    a.getOrElse(Array())
  }
}
