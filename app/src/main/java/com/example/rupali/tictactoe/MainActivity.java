package com.example.rupali.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout rootLayout;
    final static int PLAYER_O=1;
    final static int PLAYER_X=2;
    final static int NO_PLAYER=-1;

    int size=3;
    static int current_player;
    static boolean gameOver;
    TTTButton board[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout=findViewById(R.id.rootLayout);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Toast.makeText(this,"Welcome "+bundle.getString("player1")+", "+bundle.getString("player2"),Toast.LENGTH_LONG).show();
        initGame();
    }

    private void initGame() {
        board=new TTTButton[size][size];
        current_player=PLAYER_O;
        gameOver=false;
        setUpBoard();
    }

    private void setUpBoard() {
        rootLayout.removeAllViews();
        for(int row=0;row<size;row++){
            LinearLayout rowLayout=new LinearLayout(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            rowLayout.setLayoutParams(params);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for(int col=0;col<size;col++){
                TTTButton button=new TTTButton(this);
                LinearLayout.LayoutParams buttonParams=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(buttonParams);
                button.setOnClickListener(this);
                rowLayout.addView(button);
                board[row][col]=button;
            }
            rootLayout.addView(rowLayout);
        }
    }

    @Override
    public void onClick(View view) {
        TTTButton button= (TTTButton) view;
        if(!gameOver){
            button.setPlayer(current_player);
            checkGameOver();
            togglePlayer();
        }
    }
    private void checkGameOver() {
        //Check row

        for(int row=0;row<size;row++){
            boolean rowSame=true;
            TTTButton firstPlayer=board[row][0];
            for(int col=0;col<size;col++){
                TTTButton currentPlayer=board[row][col];
                if(currentPlayer.isEmpty()||currentPlayer.getPlayer()!=firstPlayer.getPlayer()){
                    rowSame=false;
                    break;
                }
            }
            if(rowSame){
               int player=firstPlayer.getPlayer();
               onGameOver(player);
               return;
            }
        }
        //Check col
        for(int col=0;col<size;col++){
            boolean colSame=true;
            TTTButton firstPlayer=board[0][col];
            for(int row=0;row<size;row++){
                TTTButton currentPlayer=board[row][col];
                if(currentPlayer.isEmpty()||currentPlayer.getPlayer()!=firstPlayer.getPlayer()){
                    colSame=false;
                    break;
                }
            }
            if(colSame){
                int player=firstPlayer.getPlayer();
                onGameOver(player);
                return;
            }
        }
        //First diag
        boolean firstSame=true;
        TTTButton firstPlayer=board[0][0];
        for(int i=0;i<size;i++){
            TTTButton currentPlayer=board[i][i];
            if(currentPlayer.isEmpty()||currentPlayer.getPlayer()!=firstPlayer.getPlayer()) {
                firstSame = false;
                break;
            }
        }
        if(firstSame){
            int player=firstPlayer.getPlayer();
            onGameOver(player);
            return;
        }
        //SecondDiag
        boolean secondSame=true;
        firstPlayer=board[0][size-1];
        for(int i=0;i<size;i++){
            TTTButton currentPlayer=board[i][size-i-1];
            if(currentPlayer.isEmpty()||currentPlayer.getPlayer()!=firstPlayer.getPlayer()) {
                secondSame = false;
                break;
            }
        }
        if(secondSame){
            int player=firstPlayer.getPlayer();
            onGameOver(player);
            return;
        }
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                if(board[row][col].getPlayer()==NO_PLAYER){
                    return;
                }
            }
        }
        onGameOver(NO_PLAYER);

    }

    private void onGameOver(int player) {
        if(player==PLAYER_O){
            Toast.makeText(this,"Player1 won ",Toast.LENGTH_SHORT).show();
        } else if (player == PLAYER_X) {
            Toast.makeText(this,"Player2 won",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        }
        gameOver=true;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j].setEnabled(false);
            }
        }

    }

    private void togglePlayer() {
        current_player=current_player==PLAYER_X?PLAYER_O:PLAYER_X;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.size3){
            size=3;
            item.setChecked(true);
            initGame();
        }
        else if(id==R.id.size4){
            size=4;
            item.setChecked(true);
            initGame();
        }
        else if(id==R.id.size5){
            size=5;
            item.setChecked(true);
            initGame();
        }
        else if(id==R.id.reset){
            initGame();
        }
        return super.onOptionsItemSelected(item);
    }
}
