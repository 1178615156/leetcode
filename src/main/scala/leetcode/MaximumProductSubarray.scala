package leetcode

object MaximumProductSubarray {

  def maxProduct(nums: Array[Int]): Int = {
    val lens = nums.foldLeft(List(List.empty[Int]))((acc, e) => if(e == 0) Nil :: acc else (e :: acc.head) :: acc.tail)
    val max = lens.map(countMaxProduct).max
    if(lens.length > 1)
      math.max(0, max)
    else
      max
  }


  def evalRPN(tokens: Array[String]): Int = {
//    def impl(l:List[Int],r:List[String]):Int ={
//      r match{
//        case Nil => l.head
//        case "+" :: tail => impl((l(0) + l(1)) :: l.tail.tail , tail)
//        case "-" :: tail => impl((l(0) - l(1)) :: l.tail.tail , tail)
//        case "*" :: tail => impl((l(0) * l(1)) :: l.tail.tail , tail)
//        case "/" :: tail => impl((l(0) / l(1)) :: l.tail.tail , tail)
//        case e :: tail => impl(e.toInt :: l , tail)
//      }
//    }
//    impl(Nil,tokens.toList)

    tokens.foldLeft(List.empty[Int]){
      case (acc,"+") => (acc(1) + acc(0)) :: acc.tail.tail
      case (acc,"-") => (acc(1) - acc(0)) :: acc.tail.tail
      case (acc,"*") => (acc(1) * acc(0)) :: acc.tail.tail
      case (acc,"/") => (acc(1) / acc(0)) :: acc.tail.tail
      case (acc, e ) => e.toInt :: acc
    }
    ???
  }

  def countMaxProduct(nums: Seq[Int]): Int = {
    if(nums.isEmpty) 0
    else {

      var products = nums.product
      var i = 0
      var passProducts = 1

      var max = math.max(products, products /nums(0))

      products /= nums(0)
      while (i < nums.length - 1) {
        passProducts *= nums(i)
        i += 1
        products /= nums(i)
        max = math.max(max, math.max(passProducts, products))
      }
      max
    }
  }

  def main(args: Array[String]): Unit = {
    //    println(countMaxProduct(Seq(1, 2, -3, 5, 6, -7, 8, 9)))
    println(maxProduct(Array(-4, -3, -2)))
  }
}
