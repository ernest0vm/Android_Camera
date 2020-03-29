package com.ernestovaldez.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Intent photoIntent;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        photoIntent = new Intent(this, PhotoActivity.class);
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {

        if (edtEmail.getText().length() == 0) {
            Log.e("login error", "email is empty");
            Toast.makeText(this, R.string.email_is_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (edtPassword.getText().length() == 0) {
            Log.e("login error", "password is empty");
            Toast.makeText(this, R.string.password_is_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        edtEmail.setText("");
        edtPassword.setText("");
        startActivity(photoIntent);
    }
}
