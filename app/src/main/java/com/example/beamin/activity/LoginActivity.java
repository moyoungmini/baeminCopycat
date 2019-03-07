package com.example.beamin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.beamin.HttpConnection;
import com.example.beamin.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText id;
    private EditText pw;
    private TextView signUpBt;
    private Button loginBt;

    private HttpConnection httpConn;
    public static Activity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        httpConn = HttpConnection.getInstance();
        loginActivity = this;

        id = findViewById(R.id.login_id_et);
        pw = findViewById(R.id.login_pw_et);
        signUpBt = findViewById(R.id.login_signup_tv);
        loginBt = findViewById(R.id.login_login_bt);

        loginBt.setOnClickListener(this);
        signUpBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_login_bt) {
            login();
        } else if (v.getId() == R.id.login_signup_tv) {
            Intent intent = new Intent(this, SignupPhoneActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        new Thread() {
            public void run() {
                try {
                    httpConn.login(id.getText().toString(), pw.getText().toString(), callback);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d("Error", "콜백오류:" + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try {
                JSONObject jsonObject = new JSONObject(response.body().string());

                if (jsonObject.getInt("code") == 200) {
                    MainActivity.userNickname = jsonObject.getJSONObject("data").getString("userEmailId");
                    MainActivity.userID = jsonObject.getJSONObject("data").getString("userNickname");
                    MainActivity.loginSuccess = true;
                    finish();
                }
                //JSON Passing
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    };
}