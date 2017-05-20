package com.example.mkarma.testappsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by m.karma on 20.05.2017.
 */

public class SuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_successful);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EMAIL_MESSAGE);

        TextView emailTextView = (TextView)findViewById(R.id.textEmail);
        emailTextView.setText(message);

        //находим кнопку "Выход"
        Button exitButton = (Button)findViewById(R.id.buttonExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //переход на закладку "activity_main"
                finish();
            }
        });
    }
}
