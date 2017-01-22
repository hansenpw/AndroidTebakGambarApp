package com.project.hansen.tebakgambartrial;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String jawaban;
    ImageView gambar;
    EditText teks;
    Button jawab;
    TextView skor;
    int j, k;
    SharedPreferences saveskor;
    int currentscore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gambar = (ImageView)findViewById(R.id.gambar);
        teks = (EditText)findViewById(R.id.teks);
        jawab = (Button)findViewById(R.id.jawab);
        skor = (TextView)findViewById(R.id.skor);
        saveskor = getSharedPreferences("saveskor", 0);
        final int score = saveskor.getInt("score", 0);
        skor.setText("Highscore: " + score);
        j = CreateQuestion();
        k = j;
        jawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = teks.getText().toString();
                if(input.equals(jawaban)){
                    Toast.makeText(MainActivity.this, "Jawaban anda benar YEAY!!!", Toast.LENGTH_LONG).show();
                    while (k==j) {
                        j = CreateQuestion();
                    }
                    currentscore++;
                    if(currentscore>saveskor.getInt("score", 0)){
                        SharedPreferences.Editor editor = saveskor.edit();
                        editor.putInt("score", currentscore);
                        editor.commit();
                        skor.setText("Highscore: " + currentscore);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Jawaban anda salah. Coba Lagi!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public int CreateQuestion(){
        Random random = new Random();
        int i = random.nextInt(3);
        switch (i){
            case 0:
                gambar.setImageResource(R.drawable.a);
                jawaban = "idrive";
                break;
            case 1:
                gambar.setImageResource(R.drawable.b);
                jawaban = "Run Fish Run";
                break;
            case 2:
                gambar.setImageResource(R.drawable.c);
                jawaban = "CD";
                break;
        }
        return i;
    }
}
