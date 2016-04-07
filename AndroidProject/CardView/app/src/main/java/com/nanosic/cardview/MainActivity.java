package com.nanosic.cardview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;


    private void setPalette(ImageView v, final TextView tV) {
       Bitmap bitmap = ((BitmapDrawable)(v).getDrawable()).getBitmap();
       // Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                if (swatch != null) {
                    tV.setTextColor(swatch.getTitleTextColor());
                    //tV.setBackgroundColor(swatch.getRgb());
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.myImageView);
        textView = (TextView) findViewById(R.id.myTextView);
        setPalette(imageView, textView);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
//        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                Palette.Swatch swatch = palette.getVibrantSwatch();
//                if (swatch != null) {
//                    textView.setBackgroundColor(swatch.getRgb());
//                    textView.setTextColor(swatch.getTitleTextColor());
//                } else {
//                    System.out.println("swatch == null");
//                }
//            }
//        });
    }
}
