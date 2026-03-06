# Wordle

A lightweight, custom-built Wordle clone for Android.

🚀 The Story Behind the Project
This app was developed in exactly one week. It started as a "just for fun" project because I found most Wordle versions on Google Play to be cluttered with ads, slow, or just visually unappealing. I wanted a clean, fast, and authentic experience, so I built my own.

✨ Features
Custom Word Engine: Picks a random 5-letter word for every new game.

Smart Keyboard: A dynamic keyboard that updates key colors (Green, Yellow, Gray) based on your progress, helping you track used letters.

Validation Logic: Integrated dictionary check to ensure only valid words are submitted.

Clean Design: Focused on the core gameplay without distractions.

🛠️ How it Works (Under the Hood)
The project follows SOLID principles to ensure the code is maintainable and scalable:

Arhitecture: I separated the concerns using specialized Managers.

GridManager: Handles the 6x5 display grid and cell coloring.

KeyboardManager: Manages the on-screen keyboard, event listeners, and color states.

GameLogic: The "brain" of the game that compares guesses against the target word.

WordManager: Responsible for reading assets and word validation.

Callback Pattern: Used a KeyboardCallback interface to decouple the UI from the keyboard logic, making the interaction between components seamless.

📚 Assets & Credits
The word list (dictionary) used in this project was sourced from a public GitHub repository. Big thanks to the community for providing clean datasets for word games!

🔧 Installation
Clone this repository.

Open in Android Studio.

Ensure the assets folder contains word_bank.txt and valid_words.txt.

Build and run on your device (API 32+ recommended).
