from functools import reduce

print(reduce(lambda l, r: l ^ r, [1, 1, 2, 3, 3, 4, 4]))