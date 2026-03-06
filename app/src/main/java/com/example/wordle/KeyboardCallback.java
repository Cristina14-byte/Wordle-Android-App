package com.example.wordle;

public interface KeyboardCallback {
    void onLetterKey(String letter);
    void onEnterKey();
    void onDeleteKey();
}