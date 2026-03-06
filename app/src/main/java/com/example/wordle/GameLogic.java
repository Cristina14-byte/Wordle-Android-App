package com.example.wordle;

public class GameLogic {
    private final String targetWord;

    public GameLogic(String targetWord) {
        this.targetWord = targetWord.toUpperCase();
    }

    public LetterStatus[] checkGuess(String guess) {
        guess = guess.toUpperCase();
        LetterStatus[] result = new LetterStatus[5];
        char[] targetChars = targetWord.toCharArray();
        char[] guessChars = guess.toCharArray();

        for (int i = 0; i < 5; i++) {
            if (targetChars[i] == guessChars[i]) {
                result[i] = LetterStatus.CORRECT;
                targetChars[i] = '#';
            }
        }

        for (int i = 0; i < 5; i++) {
            if (result[i] == null) {
                boolean found = false;
                for (int j = 0; j < 5; j++) {
                    if (guessChars[i] == targetChars[j]) {
                        result[i] = LetterStatus.MISPLACED;
                        targetChars[j] = '#';
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result[i] = LetterStatus.WRONG;
                }
            }
        }
        return result;
    }

    public boolean isWinningGuess(LetterStatus[] results) {
        for (LetterStatus status : results) {
            if (status != LetterStatus.CORRECT) return false;
        }
        return true;
    }
}