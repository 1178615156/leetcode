from typing import List


class Solution:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people = sorted(people)
        size = len(people)

        start = 0
        end = size - 1
        count = 0
        while start <= end:
            if people[start] + people[end] <= limit:
                start += 1
            count += 1
            end -= 1
        return count


if __name__ == '__main__':
    s = Solution()
    # print(s.numRescueBoats([3, 2, 2, 1], 3))
    # print(s.numRescueBoats([1, 2], 3))
    # print(s.numRescueBoats([3, 5, 3, 4], 5))
    print(s.numRescueBoats([2, 49, 10, 7, 11, 41, 47, 2, 22, 6, 13, 12, 33, 18, 10, 26, 2, 6, 50, 10], 50))
