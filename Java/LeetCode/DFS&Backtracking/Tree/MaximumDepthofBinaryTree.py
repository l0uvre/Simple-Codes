## LC 104 -- Tree, DFS
from typing import Optional 
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        depth = 0
        if root is not None:
            depth += 1
            leftDepth = self.maxDepth(root.left) if root.left is not None else 0
            rightDepth = self.maxDepth(root.right) if root.right is not None else 0
            depth += max(leftDepth, rightDepth)
        return depth

