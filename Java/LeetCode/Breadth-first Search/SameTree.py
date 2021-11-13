## LC 100
from collections import deque 
from typing import Optional 

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    '''
    BFS version. Practicing BFS implementation in Python.
    '''
    def isSameTree(self, p: Optional[TreeNode], 
            q: Optional[TreeNode]) -> bool:
        if p is None:
            return (q is None)
        elif q is None:
            return (p is None)
        que = deque()
        que.append(p)
        que.append(q)
        while len(que) > 0:
            nodesOfLevel = len(que) // 2
            nodesFirst = [] # the values for nodes in the current level of the first tree
            nodesSecond = [] # the values for nodes in the current level of the second tree
            # traverse the first tree in the level
            for i in range(0, nodesOfLevel):
                currNode = que.popleft()
                if currNode is not None:
                    nodesFirst.append(currNode.val)
                    que.append(currNode.left)
                    que.append(currNode.right)
                else:
                    nodesFirst.append(None)

            
            for j in range(0, nodesOfLevel):
                currNode = que.popleft()
                if currNode is not None:
                    nodesSecond.append(currNode.val)
                    que.append(currNode.left)
                    que.append(currNode.right)
                else:
                    nodesSecond.append(None)

            if nodesFirst != nodesSecond:
                return False

        return True


        
