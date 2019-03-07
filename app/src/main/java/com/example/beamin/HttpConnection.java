package com.example.beamin;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpConnection {

    private OkHttpClient client;
    private static HttpConnection instance = new HttpConnection();

    public static HttpConnection getInstance() {
        return instance;
    }

    private HttpConnection() {
        this.client = new OkHttpClient();
    }

    public void signUp(String id, String pw, String name,String phone, Callback callback) throws JSONException { //POST
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("hostURL")
                .addPathSegment("Segment")
                .build();
        JSONObject object = new JSONObject();

        object.put("userEmailId", id);
        object.put("userPw", pw);
        object.put("userNickname",name);
        object.put("userPhoneNumber",phone);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                object.toString()
        );


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void signUpPhoneSend(String phone, Callback callback) throws JSONException { //POST
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("hostURL")
                .addPathSegment("Segment")
                .build();
        JSONObject object = new JSONObject();

        object.put("phoneNumber", phone);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                object.toString()
        );


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void signUpPhoneReceive(String number,String phone, Callback callback) throws JSONException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("hostURL")
                .addPathSegment("Segment")
                .build();
        JSONObject object = new JSONObject();

        object.put("phoneNumber", phone);
        object.put("authNumber",number);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                object.toString()
        );


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void login(String id, String pw,Callback callback) throws JSONException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("hostURL")
                .addPathSegment("Segment")
                .build();
        JSONObject object = new JSONObject();

        object.put("userEmailId", id);
        object.put("userPw", pw);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                object.toString()
        );


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void mapSearchList(String query,Callback callback){
        try {
            OkHttpClient client = new OkHttpClient();
            String input = URLEncoder.encode(query,"utf-8");
            String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query="+input;
            Request request = new Request.Builder()
                    .addHeader("Authorization", "Kakao Rest API Key")
                    .url(url)
                    .build();
            client.newCall(request).enqueue(callback);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void menuList(String query, Callback callback) throws JSONException {
        try {
            OkHttpClient client = new OkHttpClient();
            String input = URLEncoder.encode(query,"utf-8");
            String url = "http://hostURL/Segment?category="+input+"&latitude="+String.valueOf(37.505620)+"&longitude="+String.valueOf(126.7088113);
            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .build();
            client.newCall(request).enqueue(callback);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void menuDetail(String query,Callback callback){
        try {
            OkHttpClient client = new OkHttpClient();
            String input = URLEncoder.encode(query,"utf-8");
            String url = "http://hostURL/Segment/"+query;
            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .build();
            client.newCall(request).enqueue(callback);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}