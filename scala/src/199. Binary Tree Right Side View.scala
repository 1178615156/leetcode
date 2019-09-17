object rightSideView {

  class TreeNode(var _value: Int) {
    var value: Int      = _value
    var left : TreeNode = null
    var right: TreeNode = null
  }

  case class Entity(depth: Int, offset: Int, value: Int)

  def rightSideView(root: TreeNode): List[Int] = {
    val entitys = toEntity(root, 1, 0)
    print(entitys.toList.sortBy(_.depth))
    entitys.groupBy(e => e.depth)
      .mapValues(e=> e.toList.sortBy(e=>(e.offset,e.value)).last.value)
      .toList
      .sortBy(_._1)
      .map(_._2)
  }

  def toEntity(root: TreeNode, depth: Int, offset: Int): Set[Entity] = {
    if(root == null)
      Set()
    else
      (toEntity(root.left, depth + 1, offset - 1) union toEntity(root.right, depth + 1, offset + 1)) + Entity(depth, offset,root.value)
  }
  def main(args: Array[String]): Unit = {

  }
}