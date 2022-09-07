package com.example.gatonaianom;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView contador1, contador2, estatus;
    private Button [] buttons = new Button[9];
    private Button resetGame;
    private int player1count, player2count, count;
    boolean activePlayer;

    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] posicioneswin = {
            {0,1,2}, {3,4,5}, {6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador1 = (TextView) findViewById(R.id.contador1);
        contador2 = (TextView) findViewById(R.id.contador2);
        estatus = (TextView) findViewById(R.id.estatus);

        for(int i=0; i < buttons.length; i++){
            String buttonID = "boton_" +i;
            int resourseID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourseID);
            buttons[i].setOnClickListener(this);
        }
        int rountCount = 0;
        player1count = 0;
        player2count = 0;
        activePlayer= true;
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()));

        if(activePlayer){
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#2ADA28"));
            gameState[gameStatePointer] = 0;
        }else{
            ((Button) view).setText("0");
            ((Button) view).setTextColor(Color.parseColor("#38EE36"));
            gameState[gameStatePointer] = 0;
        }
        count++;

        if(checkWinner()){
            if(activePlayer){
                contador1 ++ ;
                updatePlayerScore();
                Toast.makeText(this, "EL jugador uno ganó", Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                contador2++;
                updatePlayerScore();
                Toast.makeText(this, "EL jugador dos ganó", Toast.LENGTH_SHORT).show();
                playAgain();
            }

        }else if(count == 9){
            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
            playAgain();
        }else{
            activePlayer = !activePlayer;
        }
        if (contador2 > contador1){
            estatus.setText("El jugador 2 va ganando");
        }else if(contador1 > contador1){
            estatus.setText("EL jugador 1 va ganando");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                playAgain();
                contador1 = 0;
                contador2 = 0;
                estatus.setText("");
                updatePlayerScore();
            }

        });
    }
public boolean checkWinner(){
     boolean ganador = false;
     for(int [] psicioneswin : posicioneswin){

        if(gameState[posicioneswin[1] == gameState(posicioneswin[0]] &&
                gameState[posicioneswin[1]] == gameState(posicioneswin[2] &&
                        gameState[posicioneswin[0]]!=2)){
            ganador = true;
        }

    }
     return ganador;
    }
    public void updatePlayerScore(){
        contador1.setText(Integer.toString(contador1));
        contador2.setText(Integer.toString(contador2));
    }
    public void playAgain(){
        count = 0;
        activePlayer = true;
    }
}