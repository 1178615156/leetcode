from typing import List, Set, Tuple
from collections import deque


class Solution:
    def shortestBridge(self, A: List[List[int]]) -> int:
        self.A: List[List[int]] = A
        self.row_size = len(A)
        self.col_size = len(A[0])

        nodes = list(self.find_node())
        isl_1 = self.find_island(nodes[0][0], nodes[0][1])
        isl_2 = set(nodes).difference(isl_1)

        depth = 0

        curr_node = set(isl_1)
        next_next = set()
        while True:
            for i1 in curr_node:
                for (i, j) in self.find_neighbor(i1[0], i1[1]):
                    if (i, j) in isl_2:
                        return depth
                    if self.A[i][j] == 0:
                        self.A[i][j] = 1
                        next_next.add((i, j))
            curr_node = next_next
            next_next = set()
            depth += 1
        # distance = 10086
        # for i1 in isl_1:
        #     for i2 in isl_2:
        #         distance = min(distance, self.distance(i1, i2))
        #         if distance == 1:
        #             break
        # return distance

    def find_node(self):
        return [
            (i, j)
            for i in range(self.row_size)
            for j in range(self.col_size)
            if self.A[i][j] == 1
        ]

    def find_island(self, i, j) -> Set[Tuple[int, int]]:
        island = set()
        neighbors = deque()
        neighbors.append((i, j))

        while neighbors:
            n = neighbors.pop()
            if n not in island and self.A[n[0]][n[1]] == 1:
                island.add(n)
                neighbors.extend(self.find_neighbor(n[0], n[1]))
        return island

    def find_neighbor(self, i, j):
        neighbor = [
            (i + 1, j),
            (i - 1, j),
            (i, j + 1),
            (i, j - 1)
        ]
        return [
            (i, j)
            for (i, j) in neighbor
            if 0 <= i < self.row_size and 0 <= j < self.col_size
        ]

    def distance(self, p1, p2) -> int:
        x = p1[0] - p2[0]
        y = p1[1] - p2[1]
        return abs(x) + abs(y) - 1


if __name__ == '__main__':
    s = Solution()
    print(1, s.shortestBridge([[1, 1, 1, 1, 1], [1, 0, 0, 0, 1], [1, 0, 1, 0, 1], [1, 0, 0, 0, 1], [1, 1, 1, 1, 1]]))
    print(2, s.shortestBridge([[0, 1, 0], [0, 0, 0], [0, 0, 1]]))
    print(1, s.shortestBridge([[0, 1], [1, 0]]))

