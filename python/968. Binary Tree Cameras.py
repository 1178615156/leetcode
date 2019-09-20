from typing import List


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x, l=None, r=None):
        self.val = x
        self.left = l
        self.right = r

    def __repr__(self):
        return f"{self.val},left:{self.left},right:{self.right}"


class Solution:
    def minCameraCover(self, root: TreeNode) -> int:
        level = self.to_level([root])
        level.append([root])
        count = [len(x) for x in level]
        return count

    def to_level(self, list: List[TreeNode]) -> List[List[TreeNode]]:
        next_level = []
        for node in list:
            if node is None:
                continue
            elif node.left is None and node.right is None:
                continue
            elif node.left is None:
                next_level.append(node.right)
            elif node.right is None:
                next_level.append(node.left)
            else:
                next_level.append(node.left)
                next_level.append(node.right)
        if not next_level:
            return []
        else:
            result = self.to_level(next_level)
            result.append(next_level)
            return result


if __name__ == '__main__':
    print(list(reversed(Solution().minCameraCover(
        TreeNode(0, TreeNode(1,
                             TreeNode(2), TreeNode(3)))
    ))))
