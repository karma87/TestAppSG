package com.example.mkarma.testappsg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by m.karma on 19.05.2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPwdField;
    private EditText mConfPwdField;

    SharedPreferences mAppStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //инициализируем переменную mAppStorage
        mAppStorage = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        //находим поля ввода
        mEmailField = (EditText)findViewById(R.id.email_field);
        mPwdField   = (EditText)findViewById(R.id.pwd_field);
        mConfPwdField = (EditText)findViewById(R.id.confirm_pwd_field);

        //находим кнопку Зарегистрировать
        Button registrationButton = (Button)findViewById(R.id.buttonRegistration);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailField.getText().toString().trim();
                String password = mPwdField.getText().toString().trim();
                String confpassword = mConfPwdField.getText().toString().trim();

                if (checkInput(email, password, confpassword)){
                    //Нужно зарегистрировать пользовател
                    saveNewEmail(email, password);

                    //переход на закладку "activity_main"
                    finish();
                }
            }
        });

    }

    private Boolean checkInput(String email, String password, String confpassword){

        if(email.length() < 6){
            Toast.makeText(RegistrationActivity.this, "E-mail адреа должен быть не меньше 6 символов!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(! android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(RegistrationActivity.this, "Неверный формат введенного e-mail адреса!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() < 4){
            Toast.makeText(RegistrationActivity.this, "Пароль должен быть не меньге 4 символов!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(! password.equals(confpassword) ){
            Toast.makeText(RegistrationActivity.this, "Проверка пароля и основной пароль не совпадают!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(mAppStorage.getString(email, null) != null ) {
            Toast.makeText(RegistrationActivity.this, "Введенный вами e-mail уже зарегистрирован!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveNewEmail(String email, String password){
        SharedPreferences.Editor editor = mAppStorage.edit();
        editor.putString(email, MD5.convert(password));
        editor.commit();
    }

}
