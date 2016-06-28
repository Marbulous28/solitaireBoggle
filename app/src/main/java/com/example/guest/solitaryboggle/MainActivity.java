package com.example.guest.solitaryboggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.handView) TextView mHandView;
    @Bind(R.id.disList) ListView mDisList;
    @Bind(R.id.wordInput) EditText mWordInput;
    String convertedLetter;

    private String joinedHand;
    private String[] vowels = {"A", "E", "I", "O", "U"};
    private String[] consonants = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    ArrayList<String> hand = new ArrayList<>();
    ArrayList<String> submittedWords = new ArrayList<>();
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        for(int i = 0; i < 6; i++){
            Random rand = new Random();
            int randomConsonants = rand.nextInt(20);
            hand.add(consonants[randomConsonants]);
        }

        for (int j = 0; j < 2; j++){
            Random randVow = new Random();
            int randomVowel = randVow.nextInt(4);
            hand.add(vowels[randomVowel]);
        }
        joinedHand = TextUtils.join(" ", hand);
        mHandView.setText(joinedHand);

        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String newWord = mWordInput.getText().toString().toUpperCase();
                for ( int i=0 ; i < newWord.length(); i ++) {
                    convertedLetter = Character.toString(newWord.charAt(i));
                    for ( int j=0 ; j < hand.size(); j ++) {
                        if(convertedLetter.equals(hand.get(j))){
                            Toast.makeText(MainActivity.this, convertedLetter + "is a letter", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }
                }
                submittedWords.add(newWord);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, submittedWords);
        mDisList.setAdapter(mAdapter);
    }
}
