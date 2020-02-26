package salle.android.projects.service_test.controller.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

import salle.android.projects.service_test.R;
import salle.android.projects.service_test.controller.callbacks.CloudinaryCallback;
import salle.android.projects.service_test.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private Button btnStaticUrl;
    private Button btnLogin;
    private Button btnList;
    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        requestPermissions(); // Microphone permission for Audio Visualizer
        enableInitialButtons();
    }

    private void initViews() {
        btnStaticUrl = findViewById(R.id.btn_static_url);
        btnStaticUrl.setEnabled(false);
        btnStaticUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StaticPlaybackActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setEnabled(false);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, Constants.NETWORK.LOGIN_OK);
            }
        });

        btnList = findViewById(R.id.btn_backend);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DynamicPlaybackActivity.class);
                startActivity(intent);
            }
        });

        btnUpload = findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.MODIFY_AUDIO_SETTINGS}, Constants.PERMISSIONS.MICROPHONE);

        }
    }



    private void enableInitialButtons() {
        btnLogin.setEnabled(true);
        btnStaticUrl.setEnabled(true);
    }

    private void enableNetworkButtons() {
        btnUpload.setEnabled(true);
        btnList.setEnabled(true);
        btnLogin.setText(getResources().getString(R.string.state_connected));
        btnLogin.setEnabled(false);
        btnLogin.setBackground(ContextCompat.getDrawable(this, R.drawable.back_round_connected));
    }

    private void enableAllButtons() {
        btnUpload.setEnabled(true);
        btnList.setEnabled(true);
        btnLogin.setEnabled(true);
        btnStaticUrl.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.NETWORK.LOGIN_OK) {
            enableNetworkButtons();
            if (resultCode == RESULT_OK) {}
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
