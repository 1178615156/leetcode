from typing import List
from collections import deque


class Solution:
    def findCircleNum(self, M: List[List[int]]) -> int:
        def withFriend(my, students) -> List[int]:
            result = deque()
            for i in students:
                if i & my == 0:
                    result.append(i)
                else:
                    my = my | i
            result.append(my)
            return list(reversed(result))

        circle = []
        friends = list(map(lambda l: int("".join(map(str, l)), 2), M))
        while friends:
            new_friend = withFriend(friends[0], friends)
            if len(friends) == len(new_friend):
                circle.append(friends[0])
                friends = friends[1:]
            else:
                friends = new_friend

        return len(circle)