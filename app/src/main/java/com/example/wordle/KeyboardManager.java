package com.example.wordle;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;
import java.util.Map;

public class KeyboardManager {
    private final Activity activity;
    private final Map<Character, LetterStatus> keyStatuses = new HashMap<>();
    private final String keys = "QWERTYUIOPASDFGHJKLZXCVBNM";

    public KeyboardManager(Activity activity) {
        this.activity = activity;
    }

    public void init(KeyboardCallback callback) {
        for (char key : keys.toCharArray()) {
            String btnId = "key_" + key;
            int resId = activity.getResources().getIdentifier(btnId, "id", activity.getPackageName());
            Button btn = activity.findViewById(resId);
            if (btn != null) btn.setOnClickListener(v -> callback.onLetterKey(String.valueOf(key)));
        }
        activity.findViewById(R.id.key_enter).setOnClickListener(v -> callback.onEnterKey());
        activity.findViewById(R.id.key_backspace).setOnClickListener(v -> callback.onDeleteKey());
    }

    public void updateStatuses(String guess, LetterStatus[] results) {
        for (int i = 0; i < 5; i++) {
            char letter = guess.charAt(i);
            LetterStatus newStatus = results[i];
            LetterStatus currentStatus = keyStatuses.get(letter);
            if (shouldUpdate(currentStatus, newStatus)) {
                keyStatuses.put(letter, newStatus);
                applyColorToKey(letter, newStatus);
            }
        }
    }

    private boolean shouldUpdate(LetterStatus current, LetterStatus next) {
        if (current == null) return true;
        if (current == LetterStatus.CORRECT) return false;
        if (current == LetterStatus.MISPLACED && next == LetterStatus.CORRECT) return true;
        return false;
    }

    private void applyColorToKey(char letter, LetterStatus status) {
        String btnId = "key_" + letter;
        int resId = activity.getResources().getIdentifier(btnId, "id", activity.getPackageName());
        Button btn = activity.findViewById(resId);
        if (btn != null) {
            int color;
            switch (status) {
                case CORRECT: color = Color.parseColor("#6aaa64"); break;
                case MISPLACED: color = Color.parseColor("#c9b458"); break;
                default: color = Color.parseColor("#787c7e"); break;
            }
            btn.setBackgroundTintList(ColorStateList.valueOf(color));
            btn.setTextColor(Color.WHITE);
        }
    }

    public void reset() {
        keyStatuses.clear();
        for (char key : keys.toCharArray()) {
            String btnId = "key_" + key;
            int resId = activity.getResources().getIdentifier(btnId, "id", activity.getPackageName());
            Button btn = activity.findViewById(resId);
            if (btn != null) {
                btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D3D6DA")));
                btn.setTextColor(Color.BLACK);
            }
        }
    }
}