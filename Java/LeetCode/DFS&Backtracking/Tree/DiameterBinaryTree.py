# Definition for a binary tree node.
from typing import Optional

## LC 543 Tree, DFS, DP
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        diameter = float('-inf')
        
        '''
        Return the maximum level of breadth can be gained from a node 
        '''
        def gainFrom(node : TreeNode): -> int
            nonlocal diameter 
            if node is None:
                return -1
            else:
                leftGain = gainFrom(node.left) + 1
                rightGain = gainFrom(node.right) + 1
                diameter = max(diameter, leftGain + rightGain)
                return max(leftGain, rightGain)
            

        gainFrom(root)
        return int(diameter) 

