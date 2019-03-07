package com.example.beamin.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;
import com.example.beamin.HttpConnection;
import com.example.beamin.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SignupPhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backView;
    private EditText phoneEdit;
    private Button certBt;
    private EditText cerNum;
    private Button finishBt;
    private HttpConnection httpConn = HttpConnection.getInstance();
    public Context con;
    private String result;
    public static Activity signupPhoneActivity;
    private String phone;
    private boolean selectCertReceive = false;
    private boolean selectCertFinish = false;
    private boolean selectReceive=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_phone);

        signupPhoneActivity = this;
        con = getApplicationContext();

        backView = findViewById(R.id.signup_phone_back_tv);
        phoneEdit = findViewById(R.id.signup_phone_phone_et);
        certBt = findViewById(R.id.signup_phone_cert_receive_bt);
        cerNum = findViewById(R.id.signup_phone_cer_num_et);
        finishBt = findViewById(R.id.signup_phone_finish_bt);

        backView.setOnClickListener(this);
        certBt.setOnClickListener(this);
        finishBt.setOnClickListener(this);

        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius(4);
        shape.setColor(Color.parseColor("#d8d8d8"));
        certBt.setBackground(shape);
        //Set certBt background shape of radius and color

        cerNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(selectCertReceive && selectReceive){
                    selectCertFinish=true;
                }

                if(selectCertFinish){
                    finishBt.setBackgroundColor(Color.parseColor("#1ad2d2"));
                }
                else{
                    finishBt.setBackgroundColor(Color.parseColor("#d8d8d8"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Set button activation or disabled

        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selectCertReceive=false;
                String phoneText = phoneEdit.getText().toString();
                if(phoneText.length()==11){
                        selectCertReceive=true;
                }

                if(selectCertReceive){
                    certBt.setBackgroundColor(Color.parseColor("#1ad2d2"));
                }
                else{
                    certBt.setBackgroundColor(Color.parseColor("#d8d8d8"));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Set button activation or disabled
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signup_phone_back_tv){
            finish();
        }
        else if(v.getId() == R.id.signup_phone_cert_receive_bt && selectCertReceive) {
            try {
                String tmp = phoneEdit.getText().toString();
                String a = tmp.substring(0,3);
                String b = tmp.substring(3,7);
                String c = tmp.substring(7,11);
                phone = a+"-"+b+"-"+c;
                httpConn.signUpPhoneSend(phone,callbackSend);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(v.getId() == R.id.signup_phone_finish_bt && selectCertFinish){
            receiveData();
        }
    }

    private void receiveData() {
        new Thread() {
            public void run() {
                try {
                    httpConn.signUpPhoneReceive(cerNum.getText().toString(),phone,callbackReceive);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //Asynchronous active

    private final Callback callbackSend = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Error", "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try {
                JSONObject jsonObject = new JSONObject(response.body().string());

                if (jsonObject.getInt("code") == 200) {
                    selectReceive=true;
                    if(selectReceive){
                        Log.i("fsadf","sdf");
                    }
                    result = String.valueOf(jsonObject.getJSONObject("data").getInt("authNumber"));
                }
                else{
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(con, "010-xxxx-xxxx으로 기입해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //JSON Passing
        }
    };
    //Callback  phone Send

    private final Callback callbackReceive = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Error", "콜백오류:"+e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try {
                final JSONObject jsonObject = new JSONObject(response.body().string());
                if (jsonObject.getInt("code") == 200) {
                    Intent intent = new Intent(con, SignupActivity.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    finish();
                }
                else{
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                Toast.makeText(con, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                //JSON Passing
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    //Callback finish button

}