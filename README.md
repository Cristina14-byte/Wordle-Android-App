# Wordle Android Clone

A Wordle replica built natively for Android.

## 🚀 The Story Behind the Project
This app was developed in **one week**. It started as a personal challenge and a "just for fun" project because I was not satisfied with the versions available on Google Play. Most existing apps are:
* Cluttered with advertisements.
* Containing in-game purchases.
* Visually unappealing or overcomplicated.

I wanted to create an experience that is **100% ad-free and easy to use**, staying true to the original game's essence. Another reason was my wish to play wordle more times than once a day.

## ✨ Features
* **Smart Virtual Keyboard:** Updates in real-time with color feedback (Green, Yellow, Gray).
* **Color Priority Logic:** Prevents a key from changing back to yellow if it has already been identified as green.
* **Word Validation:** Guesses are checked against a dictionary to ensure they are valid words.
* **Custom Word Engine:** Picks a random 5-letter word for every session.
* **Minimalist UI:** Focused entirely on the gameplay with no distractions.

## 📚 Assets & Credits
The dictionaries (`word_bank.txt` and `valid_words.txt`) used in this project were sourced from a public repository on **GitHub**. Special thanks to the open-source community for providing clean word lists for developer projects. However the list is not updated, there are many words missing.

## 🔧 Installation & Setup
To run this project on your device:
1. Clone this repository.
2. Open the project in **Android Studio**.
3. Ensure the `assets` folder contains your dictionary files.
4. Build and run (Compatible with **API 32** and above).
