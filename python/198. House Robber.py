from typing import List


class Solution:
    def __init__(self):
        self.cache = dict()

    def rob(self, nums: List[int]) -> int:
        size = len(nums)
        if size == 0:
            return 0
        elif size == 1:
            return nums[0]
        elif size in self.cache:
            return self.cache[size]
        else:
            result = max(
                nums[0] + self.rob(nums[2:]),
                self.rob(nums[1:])
            )
            self.cache[size] = result
            return result


if __name__ == '__main__':
    print(Solution().rob([2, 7, 9, 3, 1]))