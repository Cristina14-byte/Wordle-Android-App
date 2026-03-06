package com.example.wordle;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class WordManager {
    private List<String> solutionBank = new ArrayList<>();
    private HashSet<String> validWords = new HashSet<>();
    private final Context context;

    public WordManager(Context context) {
        this.context = context;
        loadWords();
    }

    private void loadWords() {
        solutionBank = readFile("word-bank.txt");
        validWords = new HashSet<>(readFile("valid-words.txt"));
        validWords.addAll(solutionBank);
    }

    private List<String> readFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String cleanWord = line.trim().toUpperCase();
                if (cleanWord.length() == 5) words.add(cleanWord);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public String getRandomWord() {
        if (solutionBank.isEmpty()) return "ERROR";
        return solutionBank.get(new Random().nextInt(solutionBank.size()));
    }

    public boolean isValid(String word) {
        return validWords.contains(word.toUpperCase());
    }
}