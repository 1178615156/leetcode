from typing import List


class Solution:
    def __init__(self):
        self.cache = dict()

    def change(self, amount: int, coins: List[int]) -> int:
        return self._change(amount, sorted(coins, reverse=True))

    def _change(self, amount: int, coins: List[int]) -> int:

        def mk_result():
            if amount == 0:
                return 1
            if not coins or amount < 0:
                return 0

            coin = coins[0]
            if amount < coin:
                return self._change(amount, coins[1:])
            else:
                return (
                        self._change(amount, coins[1:]) +
                        self._change(amount - coin, coins)
                )

        key = (amount, id(coins))
        if not key in self.cache:
            result = mk_result()
            self.cache[key] = result
        return self.cache[key]


if __name__ == '__main__':
    print(Solution()._change(amount=5, coins=[1, 2, 5]))
