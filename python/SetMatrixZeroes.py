from typing import List


class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        self.matrix = matrix
        self.row_size = len(matrix)
        self.col_size = len(matrix[0])
        row_cache = set()
        col_cache = set()
        zeros = self.zeros()
        for (row, col) in zeros:
            if not row in row_cache:
                self.set_row(row)
                row_cache.add(row)
            if not col in col_cache:
                self.set_col(col)
                col_cache.add(col)

    def set_row(self, row):
        matrix = self.matrix
        for i in range(self.col_size):
            matrix[row][i] = 0

    def set_col(self, col):
        matrix = self.matrix
        for i in range(self.row_size):
            matrix[i][col] = 0

    def zeros(self):
        matrix = self.matrix
        return [
            (i, j)
            for i in range(self.row_size)
            for j in range(self.col_size)
            if matrix[i][j] == 0
        ]


if __name__ == '__main__':
    a = set()
    a.add(1)
    print(a)
