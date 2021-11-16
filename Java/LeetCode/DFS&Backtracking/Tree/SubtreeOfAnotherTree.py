from typing import Optional

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    '''
    [1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]
    [1,null,1,null,1,null,1,null,1,null,1,2]
    '''
    def isSubtree(self, root: Optional[TreeNode], 
            subRoot: Optional[TreeNode]) -> bool:
        if (root is None):
            return False
        if (subRoot is None):
            return True

        def isSameTree(leftTree, rightTree):
            if (leftTree is None):
                return rightTree is None
            if (rightTree is None):
                return leftTree is None
            
            left = leftTree.val
            right = rightTree.val
            if (left != right):
                return False
            else:
                return (isSameTree(leftTree.left, rightTree.left) 
                        and isSameTree(leftTree.right, rightTree.right) )

        if isSameTree(root, subRoot):
            return True
        else:
            return (isSubtree(root.left, subRoot) 
                    or isSubtree(root.right, subRoot))


