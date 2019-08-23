from typing import List


class Solution:
    def maxAreaOfIsland(self, grid: List[List[str]]) -> int:
        if len(grid) is 0:
            return 0

        self.row_size = len(grid)
        self.col_size = len(grid[0])
        self.grid = grid
        self.mark = self.create_matrix(self.row_size, self.col_size)

        max_area = 0
        for i in range(self.row_size):
            for j in range(self.col_size):
                if self.grid[i][j] > 0 and self.mark[i][j] is 0:
                    island = set()
                    self.do_count(i, j, island)
                    island_size = len(island)
                    for x, y in island:
                        self.mark[x][y] = island_size
                    max_area = max(max_area, island_size)
        return max_area

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

if __name__ == '__main__':
    solution = Solution()
    solution.maxAreaOfIsland([[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]])
    print(solution.mark)