package com.nanosic.stringup;

import android.content.Context;
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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private TextView textView;
    private EditText editText;
    private Button buttonVoice;
    private Button buttonConfirm;
    private Button buttonReset;

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
//                String pinyin = DBController.getInstance(MainActivity.this).find("北道主人");
//                Log.d(TAG, "onClick:  北道主人 pinyin=" + pinyin);
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
                    Toast.makeText(MainActivity.this, "你输入的成语不正确！", Toast.LENGTH_SHORT).show();
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
}
