from typing import Dict


class WordDictionary:
    def __init__(self):
        self.node: Dict[str, WordDictionary] = {}
        self.word_end = False

    def addWord(self, word: str) -> None:
        if not word:
            self.word_end = True
            return
        head = word[0]
        if head not in self.node:
            self.node[head] = WordDictionary()
        self.node[head].addWord(word[1:])

    def search(self, word: str) -> bool:
        if not word:
            return self.word_end
        head = word[0]
        if head == '.':
            for wd in self.node.values():
                if wd.search(word[1:]):
                    return True
        if head in self.node:
            return self.node[head].search(word[1:])
        return False
