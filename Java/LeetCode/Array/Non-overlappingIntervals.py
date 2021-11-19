from typing import List
from functools import cmp_to_key

## LC 435 Array, Interval
class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:

        def sort_interval(l1, l2):
            return l1[0] - l2[0]

        def isOverlapping(l1, l2):
            return not (l1[1] <= l2[0] or l1[0] >= l2[1])
        

        intervals.sort(key=cmp_to_key(sort_interval))
        leftmostInterval = intervals[0]
        numOfRemoval = 0
        for i in range(1, len(intervals)):
            currInterval = intervals[i]
            if (isOverlapping(leftmostInterval, currInterval)):
                numOfRemoval += 1
                leftmostInterval = currInterval if (currInterval[1] 
                        < leftmostInterval[1]) else leftmostInterval
            else:
                leftmostInterval = currInterval
        return numOfRemoval


def main():
    sol = Solution()
    intervals = [[1,2],[2,3],[3,4],[1,3]]
    print(sol.eraseOverlapIntervals(intervals))
    intervals = [[1,2],[1,2],[1,2]]
    print(sol.eraseOverlapIntervals(intervals))
    intervals = [[1,2],[2,3]]
    print(sol.eraseOverlapIntervals(intervals))
    intervals = [[0,2],[1,3],[2,4],[3,5],[4,6]]
    print(sol.eraseOverlapIntervals(intervals))
if __name__ == '__main__':
    main()
