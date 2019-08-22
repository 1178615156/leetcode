package leetcode

import scala.util.Try

object LongestCommonPrefix {
  def longestCommonPrefix(strs: Array[String]): String = {
    var i = 0
    val sb = new StringBuilder
    while (i < Try(strs.map(_.length).min).getOrElse(0)) {
      val char = strs(0).charAt(i)
      if(strs.map(e => e(i)).forall(_ == char)) {
        sb.append(char)
      } else {
        i = Int.MaxValue - 1
      }
      i += 1
    }

    sb.result()
  }

  def removeElement(nums: Array[Int], `val`: Int): Int = {
    var i = 0
    var j = 0
    while (i < nums.length) {
      if(nums(i) != `val`) {
        nums(j) = nums(i)
        j += 1
      }
      i += 1
    }
    j
  }
  def commonChars(A: Array[String]): List[String] = {
    A.map(_.toSet).reduce(_ & _).toList.map(_.toString)
  }

}
