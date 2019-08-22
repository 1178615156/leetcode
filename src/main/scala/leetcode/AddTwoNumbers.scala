object AddTwoNumbers {

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x   : Int      = _x

    override def toString: String = s"$x->${next}"
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val l1Values = values(l1, Nil)
    val l2Values = values(l2, Nil)
    val v1 = BigInt(l1Values.mkString(""))
    val v2 = BigInt(l2Values.mkString(""))
    val sum = v1 + v2
    val result = sum.toString().toList.map(e => (e - '0').toInt)
    mkListNode(result)
  }

  def mkListNode(values: List[Int], result: ListNode = null): ListNode = {
    if(values.isEmpty)
      result
    else {
      val node = new ListNode(values.head)
      node.next = result
      mkListNode(values.tail, node)
    }
  }

  def values(listNode: ListNode, result: List[Int]): List[Int] = {
    if(listNode == null)
      result
    else
      values(listNode.next, listNode.x +: result)
  }

  def main(args: Array[String]): Unit = {
    println(addTwoNumbers(
      mkListNode(List(3, 4, 2)),
      mkListNode(List(4, 6, 5))
    ))
  }
}