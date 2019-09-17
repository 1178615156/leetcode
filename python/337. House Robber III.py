# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def __init__(self):
        self.cache = {}

    def rob(self, root: TreeNode) -> int:
        def mk_result():
            if root is None:
                return 0
            if root.left is None and root.right is None:
                return root.val
            if root.left is None:
                return max(
                    self.rob(root.right),
                    root.val + self.rob(root.right.left) + self.rob(root.right.right)
                )
            if root.right is None:
                return max(
                    self.rob(root.left),
                    root.val + self.rob(root.left.left) + self.rob(root.left.right)
                )
            else:
                a = (root.val +
                     self.rob(root.left.left) + self.rob(root.left.right) +
                     self.rob(root.right.left) + self.rob(root.right.right)
                     )
                b = (self.rob(root.left) + self.rob(root.right))
                return max(a, b)

        if id(root) in self.cache:
            return self.cache[id(root)]
        else:
            result = mk_result()
            self.cache[id(root)] = result
            return result
