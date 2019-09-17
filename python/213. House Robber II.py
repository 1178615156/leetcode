from typing import List


class Solution:

    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        else:
            self.cache = dict()
            a = self.rob1(nums[1:])
            self.cache = dict()
            b = nums[0] + self.rob1(nums[2:-1])
            return max(a, b)

    def rob1(self, nums: List[int]) -> int:
        size = len(nums)
        if size == 0:
            return 0
        elif size == 1:
            return nums[0]
        elif size in self.cache:
            return self.cache[size]
        else:
            result = max(
                nums[0] + self.rob1(nums[2:]),
                self.rob1(nums[1:])
            )
            self.cache[size] = result
            return result


if __name__ == '__main__':
    # print(Solution().rob([2, 3, 2]))
    # print(Solution().rob([1, 2, 3, 1]))
    print(Solution().rob([2, 7, 9, 3, 1]))
