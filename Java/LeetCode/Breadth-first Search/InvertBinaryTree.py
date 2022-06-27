## LC 226 Tree, BFS, DFS
from collections import deque 
from typing import Optional 

#  Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    '''
    A BFS 
    '''
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if (root is None):
            return root
        queue = deque()
        queue.append(root)
        while len(queue) > 0:
            n = len(queue) # number of nodes in this level
            for i in range(0, n):
                node = queue.popleft()
                l = node.left
                node.left = node.right
                node.right = l
                if (node.left is not None):
                    queue.append(node.left)
                if (node.right is not None):
                    queue.append(node.right)
        return root
