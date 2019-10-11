from typing import List

class Solution:
    def removeComments(self, source: List[str]) -> List[str]:

        def parser_comment_signal(i, j):
            return i + 1, 0

        def parser_comment_mut(i_start, j_start):
            for i in range(i_start, len(source)):
                line = source[i]
                size = len(line)
                for j in range(j_start, size):
                    if j == size - 1:
                        j_start = 0
                        break
                    if line[j] == '*' and line[j + 1] == "/":
                        if j + 2 == size:
                            return i + 1, 0
                        else:
                            return i, j + 2

        result: List[str] = [""]
        ii = 0
        while ii < len(source):
            line = source[ii]
            size = len(line)
            jj = 0
            while jj < size:
                if jj == size - 1:
                    result[-1] += line[jj]
                    result.append("")
                    jj += 1
                elif line[jj] == '/' and line[jj + 1] == '/':
                    new_ii, new_jj = parser_comment_signal(ii, jj)
                    if new_ii > ii and result[-1] != "":
                        result.append("")
                    ii = new_ii
                    jj = new_jj
                    line = source[ii]
                    size = len(line)
                elif line[jj] == '/' and line[jj + 1] == '*':
                    new_ii, new_jj = parser_comment_mut(ii, jj)
                    if new_ii > ii and result[-1] != "":
                        result.append("")
                    ii = new_ii
                    jj = new_jj
                    line = source[ii]
                    size = len(line)
                else:
                    result[-1] += line[jj]
                    jj += 1
            ii += 1

        return result[0:-1]


if __name__ == '__main__':
    source = ["a//*b//*c", "blank", "d//*e/*/f"]
    result = Solution().removeComments(
        source=source)
    for i in result: print(i)
