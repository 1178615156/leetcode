from typing import List


class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        EMPTY = 0
        CHERRY = 1
        THORN = -1

        row_size = len(grid)
        col_size = len(grid[0])
        for row in reversed(range(row_size)):
            for col in reversed(range(col_size)):
                value = grid[row][col]
                if row == row_size - 1:
                    if col == col_size - 1:
                        continue
                    right = grid[row][col + 1]
                    if right == THORN or value == THORN:
                        grid[row][col] = THORN
                    else:
                        grid[row][col] = value + right
                elif col == col_size - 1:
                    down = grid[row + 1][col]
                    if down == THORN or value == THORN:
                        grid[row][col] = THORN
                    else:
                        grid[row][col] = value + down
                else:
                    right = grid[row][col + 1]
                    down = grid[row + 1][col]
                    if value == THORN or (right == THORN and down == THORN):
                        grid[row][col] = THORN
                    else:
                        grid[row][col] = value + max(right, down)

        for i in grid:
            print(i)
        return grid[0][0]


if __name__ == '__main__':
    print(Solution().cherryPickup([[0, 1, -1],
                                   [1, 0, -1],
                                   [1, 1, 1]]))
