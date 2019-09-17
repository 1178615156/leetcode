from typing import List


class Solution:
    def __init__(self):
        self.cache = dict()

    def change(self, amount: int, coins: List[int]) -> int:

        def _change():
            if amount == 0:
                return 1
            if not coins:
                return 0

            coin = coins[0]
            if amount < coin:
                return self.change(amount, coins[1:])
            else:
                return (
                        self.change(amount, coins[1:]) +
                        self.change(amount - coin, coins)
                )

        key = (amount, id(coins))
        if not key in self.cache:
            result = _change()
            self.cache[key] = result
        return self.cache[key]


if __name__ == '__main__':
    print(Solution().change(amount=5, coins=[1, 2, 5]))
