package com.chujian.ups.mtatest;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chujian.ups.mtatest.callback.InitCallBack;
import com.chujian.ups.mtatest.callback.NetCallBack;
import com.chujian.ups.mtatest.common.TestUpsCenter;
import com.chujian.ups.mtatest.net.UpsNet;
import com.chujian.ups.mtatest.okhttp.Response;
import com.chujian.ups.mtatest.util.DeviceIdUtil;
import com.chujian.ups.mtatest.util.GoogleIdUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView oaidTextView;
    private TextView imeiTextView;
    private TextView uidTextView;
    private TextView gIdTextView;
    private Button startOrEnd;
    private Button checkResult;
    private EditText clientIdEditText;

    String client_id;
    String umid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientIdEditText = findViewById(R.id.client_id);
        oaidTextView = findViewById(R.id.oaid_value);
        imeiTextView = findViewById(R.id.imei_value);
        uidTextView = findViewById(R.id.uid_value);
        gIdTextView = findViewById(R.id.googldid_value);
        startOrEnd = findViewById(R.id.startOrEnd);
        startOrEnd.setOnClickListener(this);
        checkResult = findViewById(R.id.check_result);
        checkResult.setOnClickListener(this);

        MiitHelper miitHelper = new MiitHelper(new MiitHelper.AppIdsUpdater() {
            @Override
            public void OnIdsAvalid(String oaid) {
                Log.i("ChuJianSDK", "oaid = " + oaid);
                if (!TextUtils.isEmpty(oaid)) {
                    oaidTextView.setText(oaid);
                } else {
                    oaidTextView.setText("获取oaid失败");
                }
            }
        });

        miitHelper.getDeviceIds(MainActivity.this);

        getImei();

        String deviceId = DeviceIdUtil.getDeviceId(this);
        uidTextView.setText(deviceId);

        GoogleIdUtils.getInstance().getGoogleID(this, new InitCallBack<String, String>() {
            @Override
            public void onSuccess(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(s)) {
                            gIdTextView.setText(s);
                        }else{
                            gIdTextView.setText("获取失败");
                        }
                    }
                });

            }

            @Override
            public void onFailure(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gIdTextView.setText("获取失败");
                    }
                });
            }
        });

    }

    private void getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE
            }, 100);
        }else{
            String deviceId = telephonyManager.getDeviceId();
            imeiTextView.setText(deviceId);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getImei();
                } else {
                    Toast.makeText(this, "权限已被用户拒绝", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startOrEnd:
                String operate = "start";
                if ( mtaState == MtaState.STOP){
                    operate = "start";
                }else{
                    operate = "stop";
                }
                 client_id = clientIdEditText.getText().toString();
                 umid = uidTextView.getText().toString();
                if (!TextUtils.isEmpty(client_id) && !TextUtils.isEmpty(umid)) {
                    TestUpsCenter.getInstance().setClient_id(client_id);
                    TestUpsCenter.getInstance().setUmid(umid);
                    UpsNet.modifyMtaStatus(client_id, operate, umid, new NetCallBack() {
                        @Override
                        public void onSuccess(final Response response) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int code = response.code;
                                    String body = response.body;
                                    if (code >= 200 && code < 300) {
                                        changeMtaState();
                                        JsonObject jsonObject1 = new JsonParser().parse(body).getAsJsonObject();
                                        Toast.makeText(MainActivity.this, body, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, body, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onFailure(final String s) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    });
                }
                break;
            case R.id.check_result:
                client_id = clientIdEditText.getText().toString();
                umid = uidTextView.getText().toString();
                if (!TextUtils.isEmpty(client_id) && !TextUtils.isEmpty(umid)) {
                    TestUpsCenter.getInstance().setClient_id(client_id);
                    TestUpsCenter.getInstance().setUmid(umid);
                    Intent intent = new Intent(this, TestResultActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    private void changeMtaState() {
        if (mtaState == MtaState.STOP){
            mtaState = MtaState.START;
            startOrEnd.setText("停止");
        }else if (mtaState ==MtaState.START){
            mtaState = MtaState.STOP;
            startOrEnd.setText("开始");
        }
    }

    enum MtaState{
        START,
        STOP
    }

    MtaState mtaState = MtaState.STOP;
}
