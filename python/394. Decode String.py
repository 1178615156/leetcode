class Solution:
    def decodeString(self, s: str) -> str:
        def parser(curr):
            result = ""
            num = 0
            while curr < len(s):
                char = s[curr]
                if '0' <= char <= '9':
                    num = 10 * num + int(char)
                    curr += 1
                elif char == '[':
                    r, i = parser(curr + 1)
                    result += num * r
                    curr = i
                    num = 0
                elif char == ']':
                    return result, curr + 1
                else:
                    result += s[curr]
                    curr += 1
            return  result

        return parser(0)


if __name__ == '__main__':
    print(Solution().decodeString("3[a]2[bc]"))
    print(Solution().decodeString('2[abc]3[cd]ef'))
