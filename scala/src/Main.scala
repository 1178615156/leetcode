package leetcode

object Main {


  def subsets(nums: Array[Int]): List[List[Int]] = {
    val length = nums.length
    def subsets(i:Int): List[List[Int]] = {
      if(i >= length)
        List()
      else {
        val sb = subsets(i +1)
        List(nums(i)) +: (sb ++ sb.map(nums(i) :: _))
      }
    }
    Nil +: subsets(0)
  }

  def main(args: Array[String]): Unit = {
    println(subsets(Array(1, 2, 3)))
//    Stream()
  }
}
