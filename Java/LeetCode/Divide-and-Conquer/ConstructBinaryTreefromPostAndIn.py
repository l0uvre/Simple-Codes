##  LC 106 --- Tree, Divide and Conquer
from typing import List, Optional

#  Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:

        def buildTree(inorder, postorder, inLeft, inRight, postLeft, postRight):
            if (postRight < 0 or  postLeft > postRight 
                    or inLeft > inRight):
                return None
            
            root = TreeNode(postorder[postRight])
            indexOfRoot = -1
            for i in range(inLeft, inRight + 1):
                if (inorder[i] == root.val):
                    indexOfRoot = i
                    break
            numOfLeft = indexOfRoot - inLeft
            root.left = buildTree(inorder, postorder, inLeft, indexOfRoot - 1,
                    postLeft, postLeft + numOfLeft - 1)
            root.right = buildTree(inorder, postorder, indexOfRoot + 1, inRight,
                    postLeft + numOfLeft, postRight - 1)
            return root
        
        return buildTree(inorder, postorder, 0, len(inorder) - 1, 0, len(postorder) - 1)
            

