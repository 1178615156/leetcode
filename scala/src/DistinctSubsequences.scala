//package leetcode
//
//import scala.util.Try
//
//
//object DistinctSubsequences {
//
//  import scala.collection.Searching
//
//
//  def numDistinct(s: String, t: String): Int = {
//    val map = s.zipWithIndex.groupBy(_._1).mapValues(_.map(_._2).sorted).toMap
//    search(map, -1, t.toList)
//  }
//
//  def search(map: Map[Char, Seq[Int]], index: Int, s: List[Char]): Int = {
//    if(s.isEmpty)
//      0
//    else if(s.tail.isEmpty) {
//      val char = s.head
//      val list = map.getOrElse(char, List())
//      list.length - Searching.search(list).search(index).insertionPoint
//    } else {
//      map.getOrElse(s.head, Nil).filter(_ > index).map(index => search(map, index, s.tail)).sum
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//
//  }
//}
//
