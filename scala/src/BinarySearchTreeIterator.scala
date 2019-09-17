

class BinarySearchTreeIterator {

  class TreeNode(var _value: Int) {
    var value: Int      = _value
    var left : TreeNode = null
    var right: TreeNode = null
  }

  val NAN = Int.MinValue

  class BSTIterator(_root: TreeNode) {
    lazy val left  = new BSTIterator(_root.left)
    lazy val right = new BSTIterator(_root.right)

    /** @return the next smallest number */
    def next(): Int = {
      if(left.hasNext()) {
        left.next()
      } else if(_root.value != NAN) {
        val value = _root.value
        _root.value = NAN
        value
      }
      else {
        right.next()
      }

    }

    /** @return whether we have a next smallest number */
    def hasNext(): Boolean = {
      _root != null && (left.hasNext() || _root.value != NAN || right.hasNext())
    }

  }

}
