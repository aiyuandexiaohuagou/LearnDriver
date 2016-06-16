package com.godot.stringup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private static final String APPID = "=57036ffa";
    private TextView textView;
    private EditText editText;
    private Button buttonVoice;
    private Button buttonConfirm;
    private ImageView imageView;
    private Button buttonReset;
    private CardView cardView;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 0xFFFF;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.godot.stringup.R.layout.activity_main);
        SpeechUtility.createUtility(MainActivity.this, SpeechConstant.APPID + APPID);
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void init() {
        Log.d(TAG, "init: ");

        StringGenerator.getInstance(MainActivity.this);
        cardView = (CardView) findViewById(com.godot.stringup.R.id.cardView);
        textView = (TextView) findViewById(com.godot.stringup.R.id.tv_machine);
        setNewMachineInput(StringGenerator.generateAString(null));
        editText = (EditText) findViewById(com.godot.stringup.R.id.etv_input);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    buttonConfirm.callOnClick();
                    return true;
                }
                return false;
            }
        });

        /*buttonVoice = (Button) findViewById(R.id.btn_voice_input);
        buttonVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecognizerDialog isrDialog = new RecognizerDialog(MainActivity.this, new InitListener() {
                    @Override
                    public void onInit(int i) {
                        Log.d(TAG, "onInit: i=" + i);
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
        });*/

        buttonReset = (Button) findViewById(com.godot.stringup.R.id.btn_reset);
        buttonReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewMachineInput(StringGenerator.generateAString(null));
            }
        });

        buttonConfirm = (Button) findViewById(com.godot.stringup.R.id.btn_confirm);
        buttonConfirm.setOnClickListener(new OnClickListener() {
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

        imageView = (ImageView) findViewById(com.godot.stringup.R.id.image_info);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("规则")
                        .setMessage("首尾相接，同音即可。\n" +
                                    " \n" +
                                    "联系方式:378499112@qq.com\n").show();
            }
        });
    }

    private void setNewMachineInput(String s) {
        /*generate cardView background*/
        int random = new Random().nextInt(0x00FFFFFF);
        int cardBackgroundColor = 0xFF000000 + random;
        cardView.setCardBackgroundColor(cardBackgroundColor);

        /*generate text view's text color*/
        int textColor = 0x00FFFFFF - random + 0xFF000000;
        textView.setTextColor(textColor);

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.nanosic.stringup/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + "resultCode=" + resultCode);
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for (int i = 0; i < text.size(); i++) {
                Log.d(TAG, String.format("onActivityResult: text[%d]=%s", i, text.get(i)));
            }

            editText.setText(text.get(0));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.nanosic.stringup/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
}
