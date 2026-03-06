package com.example.wordle;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

public class GridManager {
    private final TextView[][] cells = new TextView[6][5];
    private final Activity activity;

    public GridManager(Activity activity) {
        this.activity = activity;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                String cellId = "cell_" + r + "_" + c;
                int resId = activity.getResources().getIdentifier(cellId, "id", activity.getPackageName());
                cells[r][c] = activity.findViewById(resId);
            }
        }
    }

    public void setLetter(int row, int col, String letter) {
        cells[row][col].setText(letter);
    }

    public String getLetter(int row, int col) {
        return cells[row][col].getText().toString();
    }

    public void applyRowColors(int row, LetterStatus[] statuses) {
        for (int i = 0; i < 5; i++) {
            int color;
            switch (statuses[i]) {
                case CORRECT: color = Color.parseColor("#6aaa64"); break;
                case MISPLACED: color = Color.parseColor("#c9b458"); break;
                default: color = Color.parseColor("#787c7e"); break;
            }
            cells[row][i].setBackgroundColor(color);
            cells[row][i].setTextColor(Color.WHITE);
        }
    }

    public void reset() {
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                cells[r][c].setText("");
                cells[r][c].setBackgroundResource(R.drawable.cell_border);
                cells[r][c].setTextColor(Color.BLACK);
            }
        }
    }
}