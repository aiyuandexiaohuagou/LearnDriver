package com.nanosic.stringup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TextView textView;
    private EditText editText;
    private Button buttonVoice;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Log.d(TAG, "init: ");
        
        textView = (TextView) findViewById(R.id.tv_machine);
        editText = (EditText) findViewById(R.id.etv_input);
        buttonVoice = (Button) findViewById(R.id.btn_voice_input);
        buttonVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinyin = DBController.getInstance(MainActivity.this).find("北道主人");
                Log.d(TAG, "onClick:  北道主人 pinyin=" + pinyin);
            }
        });
        buttonConfirm = (Button) findViewById(R.id.btn_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*check the answer*/
                String input = getInput();

                /*if the answer is right*/
                if (ResultChecker.isRight(input)) {
                    /*the machine response according to the manual input*/
                    String ret = StringGenerator.generateAString(input);
                    if (ret == null) {
                        /*the player win*/
                        Toast.makeText(MainActivity.this, "You Win !", Toast.LENGTH_SHORT).show();
                    } else {
                        setNewMachineInput(ret);
                    }
                }
            }
        });
    }

    private void setNewMachineInput(String s) {
        textView.setText(s);
    }

    private String getInput() {
        return editText.getText().toString();
    }

    @Override
    protected void onPostResume() {
        Log.d(TAG, "onPostResume: ");
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
