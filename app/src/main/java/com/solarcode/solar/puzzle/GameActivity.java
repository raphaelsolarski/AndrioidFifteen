package com.solarcode.solar.puzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {
    Button[] fieldButtons;
    Game game;
    float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scale = getResources().getDisplayMetrics().density;

        game = new Game();
        initAndPrepareButtons();
    }

    public void initAndPrepareButtons() {
        GridLayout gl = (GridLayout) findViewById(R.id.map_layout);

        fieldButtons = new Button[game.fieldsInRow * game.fieldsInColumn];

        for(int i = 0; i < fieldButtons.length; i++) {
            fieldButtons[i] = new Button(this);
            fieldButtons[i].setLayoutParams(new LinearLayout.LayoutParams((int) (50 * scale + 0.5f), (int) (50 * scale + 0.5f)));

            if(game.getInvisibleField() != i) {
                fieldButtons[i].setText(Integer.valueOf(game.getField(i)).toString());
            } else {
                fieldButtons[i].setText("");
            }

            fieldButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < fieldButtons.length; i++) {
                        if (fieldButtons[i] == v) {
                            if (game.getInvisibleField() != i) {
                                game.buttonWasClicked(i);
                                Log.d("Clicked button", String.valueOf(i));
                                break;
                            }
                        }
                    }

                    if(game.checkIfYouWon()) {
                        Intent completed = new Intent(GameActivity.this, FinishedActivity.class);
                        startActivity(completed);
                        finish();
                    }
                    updateMap();
                }
            });
            gl.addView(fieldButtons[i]);
        }
    }

    private void updateMap() {
        for(int i = 0; i < fieldButtons.length; i++) {
            if(game.getInvisibleField() != i) {
                fieldButtons[i].setText(Integer.valueOf(game.getField(i)).toString());
            } else {
                fieldButtons[i].setText("");
            }
        }
    }
}
