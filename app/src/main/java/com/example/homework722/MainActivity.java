package com.example.homework722;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phone;
    EditText message;
    Intent newCall;
    Uri uri;
    public int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
    public int MY_PERMISSIONS_REQUEST_SEND_MESSAGE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phoneText);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });

    }

    public void call(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 100);
        else{
            uri = Uri.parse("tel:" + phone.getText().toString());
            newCall = new Intent(Intent.ACTION_CALL, uri);
            startActivity(newCall);
        }
    }

    public void sendMessage(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 101);
        else{
            SmsManager smgr = SmsManager.getDefault();
            uri = Uri.parse("tel:" + phone.getText().toString());
            smgr.sendTextMessage(phone.getText().toString(),null,message.getText().toString(),null,null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "Ошибочка", Toast.LENGTH_LONG);
                }
            }
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
                } else {
                    Toast.makeText(this, "Ошибочка", Toast.LENGTH_LONG);
                }
            }
        }
    }






}
