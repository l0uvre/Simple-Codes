
class Solution:
    def findNumbers(self, nums: list[int]) -> int:
        def getNumOfDigits(num: int):
            digits = 0
            while (num > 0):
                digits += 1
                num = num // 10
            return digits
        count = 0
        for num in nums:
            if (getNumOfDigits(num) % 2 == 0):
                count += 1 

        return count

def main():
    sol = Solution ()
    nums = [12, 345, 2, 6, 7896]
    print(sol.findNumbers(nums))
    nums = [555,901,482,1771]
    print(sol.findNumbers(nums))

if __name__ == '__main__':
    main()
