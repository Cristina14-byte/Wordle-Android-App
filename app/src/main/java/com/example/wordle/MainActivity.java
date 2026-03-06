package com.example.wordle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WordManager wordManager;
    private GameLogic gameLogic;
    private KeyboardManager keyboardManager;
    private GridManager gridManager;
    private int currentRow = 0;
    private int currentCol = 0;
    private boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordManager = new WordManager(this);
        keyboardManager = new KeyboardManager(this);
        gridManager = new GridManager(this);

        keyboardManager.init(new KeyboardCallback() {
            @Override
            public void onLetterKey(String letter) { addLetter(letter); }
            @Override
            public void onEnterKey() { submitGuess(); }
            @Override
            public void onDeleteKey() { deleteLetter(); }
        });

        findViewById(R.id.btn_reset).setOnClickListener(v -> resetGame());
        startNewGame();
    }

    private void startNewGame() {
        gameLogic = new GameLogic(wordManager.getRandomWord());
        currentRow = 0;
        currentCol = 0;
        isGameOver = false;
        gridManager.reset();
        keyboardManager.reset();
    }

    private void resetGame() {
        startNewGame();
        Toast.makeText(this, "New Game!", Toast.LENGTH_SHORT).show();
    }

    private void addLetter(String letter) {
        if (isGameOver || currentCol >= 5) return;
        gridManager.setLetter(currentRow, currentCol, letter);
        currentCol++;
    }

    private void deleteLetter() {
        if (isGameOver || currentCol <= 0) return;
        currentCol--;
        gridManager.setLetter(currentRow, currentCol, "");
    }

    private void submitGuess() {
        if (isGameOver) return;
        if (currentCol < 5) {
            Toast.makeText(this, "Not enough letters!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) sb.append(gridManager.getLetter(currentRow, i));
        String guess = sb.toString();

        if (!wordManager.isValid(guess)) {
            Toast.makeText(this, "Not in word list!", Toast.LENGTH_SHORT).show();
            return;
        }

        LetterStatus[] results = gameLogic.checkGuess(guess);
        gridManager.applyRowColors(currentRow, results);
        keyboardManager.updateStatuses(guess, results);

        if (gameLogic.isWinningGuess(results)) {
            Toast.makeText(this, "Splendid!", Toast.LENGTH_LONG).show();
            isGameOver = true;
        } else if (currentRow == 5) {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show();
            isGameOver = true;
        } else {
            currentRow++;
            currentCol = 0;
        }
    }
}