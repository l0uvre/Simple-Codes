# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
## LC 687 tree, DFS, DP
class Solution:
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:
        longestValue = 0
        
        '''
        return the length of the longest branch of uni values;
        '''
        def longestUnivalueBranch(node):
            nonlocal longestValue
            if (node is None):
                return -1
            leftLen = longestUnivalueBranch(node.left)
            rightLen = longestUnivalueBranch(node.right)
            lenBranch = 0
            if (node.left is None or node.left.val == node.val):
                leftLen += 1
                lenBranch = max(lenBranch, leftLen)
            if (node.right is None or node.right.val == node.val):
                rightLen += 1
                lenBranch = max(lenBranch, rightLen)
                if (node.left is None or node.left.val == node.val):
                    longestValue = max(longestValue, leftLen + rightLen)
            longestValue = max(longestValue, lenBranch)
            return lenBranch

        longestUnivalueBranch(root)
        return longestValue

