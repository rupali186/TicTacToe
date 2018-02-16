package com.example.rupali.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * Created by RUPALI on 31-01-2018.
 */

public class StartActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void startGame(View view){
        EditText editText=findViewById(R.id.editText);
        EditText editText1=findViewById(R.id.editText1);
        String player1=editText.getText().toString();
        String player2=editText1.getText().toString();
        Intent intent=new Intent(this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("player1",player1);
        bundle.putString("player2",player2);
        intent.putExtras(bundle);
        startActivity(intent);

    }


}
