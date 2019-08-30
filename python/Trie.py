from typing import Dict


class Trie:

    def __init__(self):
        self.node: Dict[str, Trie] = {}
        self.word_end = False

    def insert(self, word: str) -> None:
        if not word:
            self.word_end = True
            return
        head = word[0]
        if head not in self.node:
            self.node[head] = Trie()
        self.node[head].insert(word[1:])

    def search(self, word: str) -> bool:
        if not word:
            return self.word_end
        head = word[0]
        if head in self.node:
            return self.node[head].search(word[1:])
        return False

    def startsWith(self, prefix: str) -> bool:
        if not prefix:
            return True
        head = prefix[0]
        if head in self.node:
            return self.node[head].startsWith(prefix[1:])
        return False


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)


if __name__ == '__main__':
    trie = Trie()
    trie.insert("apple")
    assert trie.search("apple") is True
    assert trie.search("app") is False
    assert trie.startsWith("app") is True
    assert trie.startsWith("apple") is True
    assert trie.startsWith("appz") is False
    assert trie.search("appz") is False

    trie.insert("app")
    assert trie.search("app") is True
    assert trie.search("apple") is True
