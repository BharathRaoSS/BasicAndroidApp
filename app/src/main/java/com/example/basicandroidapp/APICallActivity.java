package com.example.basicandroidapp;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.basicandroidapp.model.APIRequestInterface;
import com.example.basicandroidapp.model.JsonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apicall);
        //Initiating the API call Using Retrofit Library
        initAPICall();
    }

    private void initAPICall() {
        //Initiate the Base URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.9.29")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Initiate the Request Body along with the End Points
        APIRequestInterface request = retrofit.create(APIRequestInterface.class);
        Call<JsonResponse> call = request.getJSON();

        //Trigger the API Call which has the Response of Type JsonResponse
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(@NonNull Call<JsonResponse> call, @NonNull Response<JsonResponse> response) {
                JsonResponse jsonResponse = response.body();
                if (jsonResponse != null) {
                    alertWithStatus("Success Response\n",
                            "resultCode:" + jsonResponse.getResultCode()
                                    + "\nstatusCode:" + jsonResponse.getStatusCode()
                                    + "\nuserInput:" + jsonResponse.getUserInput()
                                    + "\nmessage:" + jsonResponse.getMessage());
                } else {
                    alertWithStatus("Improper Success Response", "Something Isn't Right...");
                }

            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                alertWithStatus("failure", t.getMessage());
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void alertWithStatus(String title, String message) {
        //Initiating the Alert Dialog
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(APICallActivity.this);

        //setting the Title, Message for Alert
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        //Hanlding Alert Action
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do Nothing it will automatically dismiss
            }
        });

        //Presenting the Alert after building it successfully
        alertDialog.show();
    }

    public void back(View view) {
        finish();
    }
}
