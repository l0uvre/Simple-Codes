class Solution:
    def duplicateZeros(self, arr: list[int]) -> None:
        """
        Do not return anything, modify arr in-place instead.
        """
        i = 0;
        while i < len(arr):
            if (arr[i] == 0):
                for j in range(len(arr) - 1, i, -1):
                    arr[j] = arr[j - 1]
                i += 1 # skip the shifted 0
            i += 1
        print(arr)


def main():
    sol = Solution()
    arr = [1,0,2,3,0,4,5,0]
    sol.duplicateZeros(arr)
    arr = [1,2,3]
    sol.duplicateZeros(arr)

        
if __name__ == '__main__':
    main()
