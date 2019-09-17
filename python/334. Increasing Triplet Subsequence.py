from typing import List


class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        if not nums:
            return False
        _min = abs(min(nums))
        for i in range(len(nums)): nums[i] = nums[i] + _min

        _min = None
        j = 0
        for value in nums:
            if j == 0 or value < _min:
                _min = value
                nums[j] = value - _min
                j += 1
            elif value > _min:
                nums[j] = value
                j += 1

        _max = nums[j - 1]
        for i in reversed(range(j)):
            if nums[i] == 0:
                pass
                # _max = 0
            elif nums[i] < _max and i > 0:
                return True
            else:
                _max = max(_max, nums[i])

        return False


if __name__ == '__main__':
    print(Solution().increasingTriplet([1,0,-1,0,10]))
    print(Solution().increasingTriplet([1, 2, -1, 3]))
    assert Solution().increasingTriplet([0, 4, 2, 1, 0, -1, -3]) is False
    assert Solution().increasingTriplet([1, 0, 0, 1]) is False
    assert Solution().increasingTriplet([1, 2, 3, 4, 5]) is True
    assert Solution().increasingTriplet([5, 4, 3, 2, 1]) is False
    assert Solution().increasingTriplet([2, 4, -2, -3]) is False
    assert Solution().increasingTriplet([1, 0, 0, 10, 0, 0, 100]) is True
