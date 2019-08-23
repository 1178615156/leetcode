from collections import namedtuple

# Hello = namedtuple("Hello", ["x", "y"])


class Hello:
    def __init__(self):
        self.x = 1
        self.y = 2

    def __iter__(self):
        return (self.x,self.y).__iter__()
    def __sub__(self, other):
        return "a"
    #
    # def __ge__(self, other):
    #     return self.__dict__[other]
hello = Hello()
x, y = Hello()

# print(x)
# print(y)
print(hello.__dict__)
print(hello["x"])