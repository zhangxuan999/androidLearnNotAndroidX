package com.chujian.ups.mtatest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chujian.ups.mtatest.R;
import com.chujian.ups.mtatest.bean.ResultInfo;

import java.util.List;

public class ResuleAdapter extends BaseQuickAdapter <ResultInfo.ResultBean.MtalogsBean, BaseViewHolder>{
    public ResuleAdapter(int layoutResId, @Nullable List<ResultInfo.ResultBean.MtalogsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ResultInfo.ResultBean.MtalogsBean item) {
        helper.setText(R.id.user_id, "user_id:"+item.getUser_id());
        helper.setText(R.id.is_dot, item.isIs_dot()+"");
        helper.setText(R.id.category, item.getCategory());
        helper.setText(R.id.created_at, item.getCreated_at());
    }
}
