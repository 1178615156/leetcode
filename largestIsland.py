from typing import List


class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        if len(grid) is 0:
            return 0

        self.row_size = len(grid)
        self.col_size = len(grid[0])
        self.grid = grid
        self.mark = self.create_matrix(self.row_size, self.col_size)
        self.id = self.create_matrix(self.row_size, self.col_size)

        count = 0
        large = 0
        for i in range(self.row_size):
            for j in range(self.col_size):
                if self.grid[i][j] > 0 and self.mark[i][j] is 0:
                    island = set()
                    self.do_count(i, j, island)
                    island_size = len(island)
                    count += 1
                    large = max(island_size, large)

                    for x, y in island:
                        self.mark[x][y] = island_size
                        self.id[x][y] = count

        for i in range(self.row_size):
            for j in range(self.col_size):
                if self.mark[i][j] is 0:
                    neighbor = self.neighbor(i, j)
                    neighbor = {self.id[x][y]: (x, y) for x, y in neighbor}.values()

                    neighbor = [self.mark[x][y] for x, y in neighbor]
                    large = max(large, sum(neighbor) + 1)

        return large

    def do_count(self, i, j, island: set):
        if (i, j) in island:
            return
        island.add((i, j))
        for x, y in self.neighbor(i, j):
            if self.grid[x][y] > 0 and (x, y) not in island:
                self.do_count(x, y, island)

    def neighbor(self, i, j):
        return [
            (x, y)
            for x, y in [
                (i, j - 1),
                (i, j + 1),
                (i - 1, j),
                (i + 1, j)
            ]
            if 0 <= x < self.row_size and 0 <= y < self.col_size
        ]

    def create_matrix(self, row, col):
        return [[0 for j in range(col)] for i in range(row)]

# if __name__ == '__main__':
#     solution = Solution()
#     result = solution.largestIsland([[1, 1], [1, 0]])
#     print(solution.mark)
#     pass
