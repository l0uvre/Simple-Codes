# LC 88
class Solution:
    def merge(self, nums1: list[int], m: int, nums2: list[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        i = m - 1 
        j = n - 1
        k = m + n - 1
        while (i >= 0 or j >= 0):
            if (j < 0):
                while (i >= 0):
                    nums1[k] = nums1[i]
                    i -= 1
                    k -= 1
            elif (i < 0):
                while (j >= 0):
                    nums1[k] = nums2[j]
                    j -= 1
                    k -= 1
            else:
                if (nums1[i] > nums2[j]):
                    nums1[k] = nums1[i]
                    i -= 1
                else:
                    nums1[k] = nums2[j]
                    j -= 1
                k -= 1
        print(nums1)
        
def main():
    nums1 = [1,2,3,0,0,0]
    m = 3
    nums2 = [2,5,6]
    n = 3
    sol = Solution()
    sol.merge(nums1, m, nums2, n)

    nums1 = [1]
    m = 1 
    nums2 = []
    n = 0
    sol.merge(nums1, m, nums2, n)

    nums1 = [0]
    m = 0
    nums2 = [1]
    n = 1
    sol.merge(nums1, m, nums2, n)

if __name__ == '__main__':
    main()
