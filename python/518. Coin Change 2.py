from typing import List


class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        cache = dict()
        coins_size = len(coins)
        coins = sorted(coins, reverse=True)

        def _change(amount: int, coins_index) -> int:

            def mk_result():
                if amount == 0:
                    return 1
                if coins_index >= coins_size or amount < 0:
                    return 0
                coin = coins[coins_index]
                if amount < coin:
                    return _change(amount, coins_index + 1)
                else:
                    return (
                            _change(amount, coins_index + 1) +
                            _change(amount - coin, coins_index)
                    )

            key = (amount, coins_index)
            if not key in cache:
                cache[key] = mk_result()
            return cache[key]

        return _change(amount, 0)
