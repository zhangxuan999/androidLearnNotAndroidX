package com.chujian.ups.mtatest;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chujian.ups.mtatest.adapter.ResuleAdapter;
import com.chujian.ups.mtatest.bean.ResultInfo;
import com.chujian.ups.mtatest.callback.NetCallBack;
import com.chujian.ups.mtatest.common.TestUpsCenter;
import com.chujian.ups.mtatest.net.UpsNet;
import com.chujian.ups.mtatest.okhttp.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestResultActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    Button startTime;
    Button endTime;
    Button query;
    private ResuleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        recyclerView = findViewById(R.id.product_list);
        startTime = findViewById(R.id.start_time);
        endTime = findViewById(R.id.end_time);
        query = findViewById(R.id.query);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        query.setOnClickListener(this);
        adapter = new ResuleAdapter(R.layout.result_item,null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        initTimePicker();
        initTimePicker2();
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//            }
//        },recyclerView);

        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(TestResultActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(TestResultActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        }).build();
        queryData();
    }

    private void queryData() {
        UpsNet.queryMtaResult(TestUpsCenter.getInstance().getClient_id(), /*TestUpsCenter.getInstance().getUmid()*/"16deb3cef6b52d1b142a1d12bd9308fc",startDate,endDate,new NetCallBack(){
            @Override
            public void onSuccess(final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int code  = response.code;
                        String body = response.body;
                        if (code >=200 && code < 300){
                            JsonObject jsonObject1 = new JsonParser().parse(body).getAsJsonObject();
                            Gson gson = new Gson();
                            ResultInfo resultInfo = gson.fromJson(body, ResultInfo.class);
                            List<ResultInfo.ResultBean.MtalogsBean> mtalogs = resultInfo.getResult().getMtalogs();
                            if (mtalogs !=null && mtalogs.size()>0){
                                adapter.replaceData(mtalogs);
                            }
                        }else{
                            Toast.makeText(TestResultActivity.this,body,Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

            @Override
            public void onFailure(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestResultActivity.this,s,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private TimePickerView pvTime;
    private TimePickerView pvTime2;
    private Date startDate;
    private Date endDate;
    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                startDate = date;
                Toast.makeText(TestResultActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }

    private void initTimePicker2() {//Dialog 模式下，在底部弹出
        pvTime2 = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                endDate = date;
                Toast.makeText(TestResultActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime2.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime2.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_time:
                if (pvTime != null){
                    pvTime.show();
                }
                break;
            case R.id.end_time:
                if (pvTime2 != null){
                    pvTime2.show();
                }
                break;
            case R.id.query:
                queryData();
                break;
            default:
                break;
        }
    }
}
