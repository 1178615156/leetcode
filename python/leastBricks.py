from typing import List

from collections import deque

from itertools import groupby


class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        wall_interspace = [
            x
            for l in wall
            for x in self.interspace(l)
        ]

        count = [len(list(v)) for k, v in groupby(sorted(wall_interspace))]
        max_count = 0
        if count:
            max_count = max(count)
        return len(wall) -max_count

    def interspace(self, l: List):
        sums = 0
        result = deque()
        for i in range(len(l) - 1):
            value = l[i]
            sums += value
            result.append(sums)
        return list(result)


