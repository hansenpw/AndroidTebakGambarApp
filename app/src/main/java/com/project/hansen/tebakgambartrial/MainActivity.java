package com.project.hansen.tebakgambartrial;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String key = "score";
    private String jawaban;
    private ImageView gambar;
    private EditText teks;
    private TextView skor;
    private int j, k;
    private SharedPreferences saveskor;
    private int currentscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up views
        gambar = (ImageView) findViewById(R.id.gambar);
        teks = (EditText) findViewById(R.id.teks);
        Button jawab = (Button) findViewById(R.id.jawab);
        skor = (TextView) findViewById(R.id.skor);

        /**
         * Set up SharedPreferences to save highscore
         * @see android.content.SharedPreferences
         */
        saveskor = getSharedPreferences("saveskor", 0);
        final int score = saveskor.getInt(key, 0);
        skor.setText("Highscore: " + score);

        // start tebak gambar session
        j = CreateQuestion();
        k = j;
        jawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = teks.getText().toString();
                if (input.equalsIgnoreCase(jawaban)) {
                    Toast.makeText(MainActivity.this, "Jawaban anda benar YEAY!!!", Toast.LENGTH_LONG).show();
                    // loop to make sure the same question doesn't show up when randomizing question
                    while (k == j) {
                        j = CreateQuestion();
                    }
                    currentscore++;
                    // save to SharedPreferences each time highscore changes
                    if (currentscore > saveskor.getInt(key, 0)) {
                        SharedPreferences.Editor editor = saveskor.edit();
                        editor.putInt(key, currentscore);
                        editor.apply();
                        skor.setText("Highscore: " + currentscore);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Jawaban anda salah. Coba Lagi!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Create Question by randomizing 3 questions
     *
     * @return id of the selected randomize question
     */
    public int CreateQuestion() {
        Random random = new Random();
        int i = random.nextInt(3);
        teks.setText("");
        switch (i) {
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
