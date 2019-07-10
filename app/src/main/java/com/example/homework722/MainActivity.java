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

public class MainActivity extends AppCompatActivity {

    EditText phone;
    EditText message;
    Intent newCall;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phoneText);

    }

    public void call(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 100);
        else{
            uri = Uri.parse("tel:" + phone.getText().toString());
            newCall = new Intent(Intent.ACTION_CALL, uri);
            startActivity(newCall);
        }
    }

    public void sendMessage(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 101);
        else{
            SmsManager smgr = SmsManager.getDefault();
            uri = Uri.parse("tel:" + phone.getText().toString());
            smgr.sendTextMessage(phone.getText().toString(),null,message.getText().toString(),null,null);
        }
    }


}
