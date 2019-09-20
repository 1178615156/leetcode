class Solution:

    def __init__(self):
        self.cache = {}

    def isScramble(self, s1: str, s2: str) -> bool:
        if (s1, s2) in self.cache:
            return self.cache[(s1, s2)]
        if len(s1) != len(s2):
            return False
        if set(s1) != set(s2):
            return False
        if len(s1) == 1:
            return s1 == s2

        size = len(s1)
        for mid in range(1, size):
            a = lambda: (self.isScramble(s1[0:mid], s2[0:mid]) and self.isScramble(s1[mid:], s2[mid:]))
            b = lambda: (self.isScramble(s1[0:mid], s2[size - mid:]) and self.isScramble(s1[mid:], s2[0:size - mid]))
            if a() or b():
                self.cache[(s1,s2)] = True
                return True
        self.cache[(s1, s2)] = False
        return False

if __name__ == '__main__':
    assert Solution().isScramble("abb", "bab")
    assert Solution().isScramble("abc", "bac")
    assert Solution().isScramble("great", "rgeat")
    assert Solution().isScramble("great", "rgtae")
    assert Solution().isScramble("great", "taerg")
    assert Solution().isScramble("abcde", "caebd") is False
    assert Solution().isScramble("a", "b") is False
