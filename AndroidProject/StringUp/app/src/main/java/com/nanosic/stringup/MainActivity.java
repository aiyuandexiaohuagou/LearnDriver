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

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private static final String APPID = "=57036ffa";
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
        SpeechUtility.createUtility(MainActivity.this, SpeechConstant.APPID+APPID);
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
                RecognizerDialog isrDialog = new RecognizerDialog(MainActivity.this, new InitListener() {
                    @Override
                    public void onInit(int i) {
                        Log.d(TAG, "onInit: i="+i);
                    }
                });

                isrDialog.setListener(new RecognizerDialogListener() {
                    @Override
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        String result = recognizerResult.getResultString();
                        Log.d(TAG, "onResult: result=" + result + ", b=" + b);
                        String text = JsonParser.parseIatResult(recognizerResult.getResultString());
                        Log.d(TAG, "onResult: text=" + text);
                        if (!b) {
                            editText.append(text);
                        }
                    }

                    @Override
                    public void onError(SpeechError speechError) {
                        Log.d(TAG, "onError: speechError=" + speechError.toString());
                    }


                });

                isrDialog.setParameter(SpeechConstant.DOMAIN, "iat");
                isrDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
                isrDialog.setParameter(SpeechConstant.ACCENT, "mandarin ");
                isrDialog.setParameter(SpeechConstant.ENGINE_TYPE, "mixed");

                isrDialog.show();
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
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
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
