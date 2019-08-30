package leetcode

object ContainerWithMostWater {

  case class Entity(height: Int, index: Int)

  def enumMaxArea(list: Seq[Entity], max: Int): Int = {
    if(list.isEmpty || list.tail.isEmpty)
      max
    else {
      val start = list.head
      val tail = list.tail
      val currMax = tail.map(e => (e.index - start.index) * math.min(e.height, start.height)).max
      enumMaxArea(list.tail, math.max(max, currMax))
    }
  }

  def clear(head: List[Entity], value: Entity, tail: List[Entity]): List[Entity] = {
    if(tail.isEmpty)
      (value :: head).reverse
    else if(value.height <= tail.head.height && (head.nonEmpty && value.height <= head.head.height))
      clear(head.tail, head.head, tail)
    else
      clear(value :: head, tail.head, tail.tail)
  }

  def maxArea(height: Array[Int]): Int = {
    val list = height.toList.zipWithIndex.map { case (value, index) => Entity(value, index) }

    enumMaxArea(clear(Nil, list.head, list.tail), 0)
  }
}
