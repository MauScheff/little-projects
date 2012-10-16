#! /usr/bin/env python

import sys
import string

INTRO = \
""" GHOST (WORD COMPLETE GAME)
    ==========================

    Compete with the computer to stay on the game. Enter a letter in your turn.
    Whoever completes a word (from WORD.lST) loses. Try to force the computer
    into a word that will make him lose, and then you'll win. Do not chose a letter
    that doesn't lead into any word, or the computer will win.

"""

class Trie:
    class Node:
        def __init__(self):
            letters = map(chr, range(65, 91))
            self.children = dict.fromkeys(letters)
            self.score = 0

        def is_leaf(self):
            return filter(None, self.children.values()) == []

    def __init__(self):
        self.root = Trie.Node()

    def __insert_word__(self, word, node, height):
        # Inserts a word into the Trie and precomputes Minimax scores.
        # See: http://en.wikipedia.org/wiki/Minimax
        if not word:
            # Favorable leaf node
            if height % 2 == 1:
                node.score = height
            # Unfavorable leaf node
            else:
                node.score = height * -1
        else:
            # In the beginning, there was nothing.
            letter = word[0]
            if not node.children[letter]:
                node.children[letter] = self.Node()

            word = word[1:]
            self.__insert_word__(word, node.children[letter], height + 1)

            # Propagate scores and precompute minimax scores
            if node.score == 0:
                node.score = node.children[letter].score
            elif height % 2 == 0:
                node.score = min(node.score, node.children[letter].score)
            else:
                node.score = max(node.score, node.children[letter].score)

    def insert_word(self, word):
        self.__insert_word__(word.upper().strip(), self.root, 0)

    def descend(self, letter):
        # Trims the tree by pointing the root to one of its children
        if self.root.children[letter] == None:
            # The chosen letter can't be extended into a word
            return True # Game Over
        self.root = self.root.children[letter]
        return False # Valid choice

    def get_score(self):
        return self.root.score

    def find_min(self):
        # Finds the child node with the minimum score
        min_letter = ""
        min_score = sys.maxint
        for letter in self.root.children:
            if self.root.children[letter] and \
            self.root.children[letter].score < min_score:
                min_letter = letter
                min_score = self.root.children[letter].score
        return min_letter

    def find_max(self):
        # Finds the child node with the maximum score
        max_letter = ""
        max_score = sys.maxint * -1
        for letter in self.root.children:
            if self.root.children[letter] and \
            self.root.children[letter].score > max_score:
                max_letter = letter
                max_score = self.root.children[letter].score
        return max_letter

    def game_over(self):
        return self.root.is_leaf()

class Ghost():
    def player_move(self):
        while True:
            choice = raw_input("Please enter a letter: ")
            if choice in string.ascii_letters:
                self.spelling = self.spelling + choice.upper()
                return self.words.descend(choice.upper()) or self.words.game_over()
            print("Invalid character, please try again.")

    def computer_move(self):
        if (self.words.get_score() <= 0):
            # Already lost, extend play!
            choice = self.words.find_min()
            self.spelling = self.spelling + choice
            print "Computer chooses: " + choice
            return self.words.descend(choice) or self.words.game_over()
        else:
            # Maximize player's pain!
            choice = self.words.find_max()
            self.spelling = self.spelling + choice
            print "Computer chooses: " + choice
            return self.words.descend(choice) or self.words.game_over()

    def main(self):
        self.words = Trie()
        self.spelling = ""
        with open("WORD.LST") as file:
            stem = "Zebra" # Dummy value
            for word in file:
                word = word.strip()
                if not word.startswith(stem):
                    if len(word) >= 4:
                        stem = word
                    self.words.insert_word(word)

        print(INTRO)

        while True:
            loser = self.player_move()
            if loser:
                print "You lose (player), I win (computer)."
                break
            loser = self.computer_move()
            if loser:
                print "I lose (computer), you win (player)."
                break
            print "Extended spelling (so far...): " + self.spelling

if __name__ == "__main__":
    Ghost().main()

