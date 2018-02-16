package com.example.rupali.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by RUPALI on 31-01-2018.
 */

public class TTTButton extends AppCompatButton{
    private int player;

    public TTTButton(Context context) {
        super(context);
        setBackgroundResource(R.drawable.button_bg);
        player=MainActivity.NO_PLAYER;
    }

    void setPlayer(int player1){
        this.player=player1;
        if(player==MainActivity.PLAYER_O){
            setBackgroundResource(R.drawable.tic_tac_toe_o);
            setText("O");
            setEnabled(false);
        }
        else if(player==MainActivity.PLAYER_X){
            setBackgroundResource(R.drawable.tic_tac_toe_x);
            setText("X");
            setEnabled(false);
        }
    }

    public int getPlayer() {
        return player;
    }

    public boolean isEmpty() {
        return player==MainActivity.NO_PLAYER;
    }
}
