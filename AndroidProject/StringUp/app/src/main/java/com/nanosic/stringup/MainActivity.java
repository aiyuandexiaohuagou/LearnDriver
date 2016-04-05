package com.nanosic.stringup;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TextView textView;
    private EditText editText;
    private Button buttonVoice;
    private Button buttonConfirm;
    private Button buttonReset;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 0xFFFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Log.d(TAG, "init: ");

        StringGenerator.getInstance(MainActivity.this);

        textView = (TextView) findViewById(R.id.tv_machine);
        setNewMachineInput(StringGenerator.generateAString(null));
        editText = (EditText) findViewById(R.id.etv_input);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    buttonConfirm.callOnClick();
                    return true;
                }
                return false;
            }
        });

        buttonVoice = (Button) findViewById(R.id.btn_voice_input);
        buttonVoice.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "请开始语音");
                    startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "找不到语音设备", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonReset = (Button) findViewById(R.id.btn_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewMachineInput(StringGenerator.generateAString(null));
            }
        });

        buttonConfirm = (Button) findViewById(R.id.btn_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*check the answer*/
                String input = getInput();
                if (Objects.equals(input, "")) {
                    Toast.makeText(MainActivity.this, "您还没输入成语！", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if the input is not a string, toast error*/
                if (!StringGenerator.isAString(input)) {
                    Toast.makeText(MainActivity.this, "你输入的不是成语！", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if the answer is right*/
                if (ResultChecker.isRight(input)) {
                    /*the machine response according to the manual input*/
                    String ret = StringGenerator.generateAString(input);
                    if (ret == null) {
                        /*the player win*/
                        Toast.makeText(MainActivity.this, "You Win !", Toast.LENGTH_SHORT).show();
                    } else {
                        setNewMachineInput(ret);
                        clearManualInput();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "你输入的成语不正确！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setNewMachineInput(String s) {
        textView.setText(s);
    }

    private void clearManualInput() {
        editText.setText(null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode="+requestCode + "resultCode=" + resultCode);
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (int i=0 ; i<text.size(); i++) {
                Log.d(TAG, String.format("onActivityResult: text[%d]=%s", i, text.get(i)));
            }

            editText.setText(text.get(0));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
