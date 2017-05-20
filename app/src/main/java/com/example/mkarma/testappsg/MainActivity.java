package com.example.mkarma.testappsg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by m.karma on 20.05.2017.
 */

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPwdField;
    public static final String APP_PREFERENCES = "app_storage";
    public static final String EMAIL_MESSAGE = "com.example.myfirstapp.EMAIL";

    SharedPreferences mAppStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализируем переменную mAppStorage
        mAppStorage = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        //находим поля ввода логина и пароля
        mEmailField = (EditText)findViewById(R.id.email_field);
        mPwdField   = (EditText)findViewById(R.id.pwd_field);

        //находим кнопку "Войти"
        Button loginButton = (Button)findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailField.getText().toString().trim();
                String password = mPwdField.getText().toString().trim();

                if(! checkInput(email, password)){
                    return;
                }

                String savedMD5 = mAppStorage.getString(email, null);//предполагаем что получаем из настроек в MD5
                String passwordMD5 = MD5.convert(password);

                //ТУТ ПРОВЕРКА ЛОГИНА и ПАРОЛЯ
                if(passwordMD5.equals(savedMD5)){
                    //переход на закладку "activity_successful"
                    Intent intent = new Intent(MainActivity.this, SuccessfulActivity.class);
                    intent.putExtra(EMAIL_MESSAGE, email);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Неверный логин и/или пароль!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //находим кнопку "Регистрация"
        Button registrationButton = (Button)findViewById(R.id.buttonRegistration);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //выведем сообщение для теста
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    private Boolean checkInput(String email, String password){

        if(TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "E-mail пустой!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Пароль пустой!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
