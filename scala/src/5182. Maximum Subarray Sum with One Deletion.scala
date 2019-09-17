object Solution {
  def maximumSum(arr: Array[Int]): Int = {
    val list = sumInc(arr.toList, Nil)
    max(list, arr.max)
  }

  def sumInc(list: List[Int], result: List[Int]): List[Int] = {
    if(list.isEmpty)
      result
    else if(list.head < 0)
      sumInc(list.tail, list.head :: result)
    else {
      val inc = list.takeWhile(_ >= 0)
      sumInc(list.drop(inc.size), inc.sum :: result)
    }
  }

  def max(list: List[Int], result: Int): Int = {
    list match {
      case Nil              => result
      case a :: Nil         => math.max(result, a)
      case a :: b :: Nil    => List(result, a, b, a + b).max
      case a :: b :: c :: _ => max(list.tail, List(result, a, b, c, a + b, b + c, a + c, a + b + c).max)
    }
  }

  def main(args: Array[String]): Unit = {
    println(maximumSum(Array(1, -2, 0, 3)))
    print(maximumSum(Array(1,-2,-2,3)))
  }
}