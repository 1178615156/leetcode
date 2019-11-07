from typing import List


class Solution:
    def removeBoxes(self, boxes: List[int]) -> int:
        size = len(boxes)
        cache = dict()

        def remove(bitmap, deep=1,parent_score=0):
            if bitmap == 0:
                return parent_score
            if bitmap in cache:
                return cache[bitmap] + parent_score
            i = 0
            max_score = 0
            while i < size:
                if bitmap & (1 << i) == 0:
                    i += 1
                    continue

                box = boxes[i]
                next_bitmap = bitmap - (1 << i)
                score = 1
                while i < size:
                    if next_bitmap & (1 << i) == 0:
                        i += 1
                    elif boxes[i] != box:
                        break
                    else:
                        next_bitmap -= (1 << i)
                        score += 1
                        i += 1
                score *= score
                score += parent_score
                score = remove(next_bitmap, deep + 1,score)
                # if score != 0:
                if score > max_score:
                    max_score = score


            result = max_score
            cache[bitmap] = result - parent_score
            return result

        rt= remove((1 << size) - 1)
        for k,v in cache.items():
            print(f"{k:0{size}b}",v)
        return rt

if __name__ == '__main__':
    print(Solution().removeBoxes([1, 2, 3]))
    print(Solution().removeBoxes([1, 2, 1]))
    print(Solution().removeBoxes([1, 3, 2, 2, 2, 3, 4, 3, 1]))
    # print(Solution().removeBoxes([3,
    #                               8,
    #                               8,
    #                               5,
    #                               5,
    #                               3,
    #                               9,
    #                               2,
    #                               4,
    #                               4,
    #                               6,
    #                               5,
    #                               8,
    #                               4,
    #                               8,
    #                               6,
    #                               9,
    #                               6,
    #                               2,
    #                               8,
    #                               6,
    #                               4,
    #                               1,
    #                               9,
    #                               5,
    #                               3,
    #                               10,
    #                               5,
    #                               3,
    #                               3,
    #                               9,
    #                               8,
    #                               8,
    #                               6,
    #                               5,
    #                               3,
    #                               7,
    #                               4,
    #                               9,
    #                               6,
    #                               3,
    #                               9,
    #                               4,
    #                               3,
    #                               5,
    #                               10,
    #                               7,
    #                               6,
    #                               10,
    #                               7]))
