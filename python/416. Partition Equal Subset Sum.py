from typing import List


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        # nums = sorted(nums)
        nums_sum = sum(nums)
        nums_size = len(nums)
        cache = dict()

        def exist_sum(i, value):
            key = (i,value)
            if i == nums_size or value < 0:
                return False
            if value == 0:
                return True
            if key in cache:
                return cache[key]

            result = exist_sum(i + 1, value - nums[i]) or exist_sum(i + 1, value)
            cache[key] = result
            return result

        if int(nums_sum / 2) * 2 != nums_sum:
            return False
        return exist_sum(0, int(nums_sum / 2))


if __name__ == '__main__':
    assert Solution().canPartition([1, 5, 11, 5])
    assert Solution().canPartition([1, 2, 3, 5]) is False
