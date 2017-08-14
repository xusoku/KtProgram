package com.davis.ktprogram.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.davis.sdj.R;
import com.davis.sdj.adapter.base.CommonBaseAdapter;
import com.davis.sdj.adapter.base.ViewHolder;
import com.davis.sdj.model.GroupBuy;
import com.davis.sdj.util.DisplayMetricsUtils;
import com.davis.sdj.util.UtilText;

import java.util.ArrayList;

import cn.iwgang.countdownview.CountdownView;

/**
 * Created by xushengfu on 2017/7/24.
 */

public class CountDownAdapter extends CommonBaseAdapter<GroupBuy> {

    public static final int GROUPBUY=0;
    public static final int PRESALE=1;
    private int type=GROUPBUY; //0团购 1预售
    public CountDownAdapter(Context context, ArrayList<GroupBuy> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void convert(final ViewHolder holder, final GroupBuy itemData, final int position) {
        ImageView iv = holder.getView(R.id.groupbuy_image);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) DisplayMetricsUtils.getWidth(), (int) (DisplayMetricsUtils.getWidth() * 0.464));
        iv.setLayoutParams(layoutParams);

        Glide.with(mContext).load(itemData.picurl)
                .placeholder(R.mipmap.img_defualt_bg)
                .error(R.mipmap.img_defualt_bg)
                .into(iv);



        TextView tv_name = holder.getView(R.id.groupbuy_title);
        TextView groupbuy_pe = holder.getView(R.id.groupbuy_pe);
        TextView groupbuy_btn = holder.getView(R.id.groupbuy_btn);
        TextView tv_price = holder.getView(R.id.groupbuy_price);
        TextView groupbuy_time = holder.getView(R.id.groupbuy_time);
        CountdownView groupbuy_count_time = holder.getView(R.id.groupbuy_count_time);
        TextView groupbuy_oldprice = holder.getView(R.id.groupbuy_oldprice);







        tv_price.setText("");
        tv_price.append(UtilText.getIndexPrice("¥"));
        if(type==GROUPBUY) {
            tv_price.append(UtilText.getBigProductDetail(itemData.ptprice, 50));
        }else{
            tv_price.append(UtilText.getBigProductDetail(itemData.price, 50));
        }



        if(type==GROUPBUY){
            tv_name.setText(itemData.pttitle);

            groupbuy_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            groupbuy_time.setVisibility(View.GONE);
            groupbuy_count_time.setVisibility(View.GONE);
            groupbuy_pe.setVisibility(View.VISIBLE);
            groupbuy_oldprice.setText("¥"+itemData.marketprice);
            groupbuy_pe.setText("已有"+itemData.salecount+"人团");

            if(itemData.ptstatus.equals("1")){
                groupbuy_btn.setBackgroundResource(R.drawable.bg_cri_red);
                groupbuy_btn.setText("马上抢");
            }else{
                groupbuy_btn.setBackgroundResource(R.drawable.bg_cri_gray);
                groupbuy_btn.setText("团购结束");
            }
        }else{
            tv_name.setText(itemData.title);
            groupbuy_btn.setText("预计发售");
            groupbuy_oldprice.getPaint().setFlags(0);
            groupbuy_pe.setVisibility(View.INVISIBLE);
            groupbuy_time.setVisibility(View.GONE);
            groupbuy_count_time.setVisibility(View.VISIBLE);
            groupbuy_oldprice.setText(itemData.sellcount+"份");
        }

        long leftTime = itemData.countdowntime-(System.currentTimeMillis());
        if (leftTime > 0) {
            groupbuy_count_time.start(leftTime);
        } else {
            groupbuy_count_time.stop();
            groupbuy_count_time.allShowZero();
        }

        groupbuy_count_time.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                GroupBuy itemData = mDatas.get(position);
                CountdownView groupbuy_count_time = holder.getView(R.id.groupbuy_count_time);
                long leftTime = itemData.countdowntime-(System.currentTimeMillis());
                if (leftTime > 0) {
                    groupbuy_count_time.start(leftTime);
                } else {
                    groupbuy_count_time.stop();
                    groupbuy_count_time.allShowZero();
                }
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                CountdownView groupbuy_count_time = holder.getView(R.id.groupbuy_count_time);
                groupbuy_count_time.stop();
            }
        });
    }


}

