package com.davis.ktprogram.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.davis.ktprogram.AppApplication;
import com.davis.ktprogram.R;
import com.davis.ktprogram.adapter.base.CommonBaseAdapter;
import com.davis.ktprogram.adapter.base.ViewHolder;
import com.davis.ktprogram.api.ApiCallback;
import com.davis.ktprogram.api.ApiInstant;
import com.davis.ktprogram.model.BaseModel;
import com.davis.ktprogram.model.Cart;
import com.davis.ktprogram.util.ToastUitl;
import com.davis.ktprogram.util.UtilText;
import com.davis.ktprogram.views.CustomAlterDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;

/**
 * Created by davis on 16/5/25.
 */
public class CartListAdapter extends CommonBaseAdapter<Cart> {

    private OnPriceChange onPriceChange;


    private Context contxt;

    public void setOnPriceChange(OnPriceChange onPriceChange) {
        this.onPriceChange = onPriceChange;
    }

    public CartListAdapter(Context context, ArrayList<Cart> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.contxt = context;
    }


    @Override
    public void convert(ViewHolder holder, final Cart itemData, int position) {

        holder.setImageByUrl(R.id.add_cart_item_iv, itemData.getPicurl());
        holder.setText(R.id.add_cart_item_title, itemData.getProductName());
        holder.setText(R.id.add_cart_item_sstandent, "规格:" + itemData.getSstandard());
        TextView textView=holder.getView(R.id.add_cart_item_price);
        textView.setText("");
        textView.append(UtilText.getBigProductDetail("¥" + itemData.getIprice()));
        textView.append("/" + itemData.getSstandard());
        holder.setText(R.id.add_cart_item_add_center, UtilText.getDivideZero((itemData.getInumber()) + ""));

        CheckBox checkBox = holder.getView(R.id.add_cart_item_checkbox);
        int size= contxt.getResources().getDisplayMetrics().densityDpi;
        if(size==160) {
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(30,30,30,30);
            checkBox.setLayoutParams(params);
            checkBox.setScaleX(2.5f);
            checkBox.setScaleY(2.5f);
        }
        RelativeLayout add_cart_relative_bottom = holder.getView(R.id.add_cart_relative_bottom);
        TextView add_cart_presell = holder.getView(R.id.add_cart_presell);
        ImageView ivDelete = holder.getView(R.id.add_cart_add_del);
        TextView addText = holder.getView(R.id.add_cart_item_add);
        final TextView text = holder.getView(R.id.add_cart_item_add_center);
        TextView minsText = holder.getView(R.id.add_cart_item_add_mins);

        if(itemData.getSelltype()==1){
            add_cart_relative_bottom.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.GONE);
            add_cart_presell.setVisibility(View.VISIBLE);
        }else{
            add_cart_relative_bottom.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            add_cart_presell.setVisibility(View.GONE);

        }



        checkBox.setChecked(itemData.getFlag());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                itemData.setFlag(isChecked);
                notifyDataSetChanged();
                onPriceChange.priceChange();
            }
        });
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float mincount=itemData.getInumber();
                if(mincount>1){}else{
                    mincount=1;
                }
                String number = text.getText().toString().trim();

                float n = (float) Float.parseFloat(number);
                n=n+mincount;
                itemData.setInumber(n);
                notifyDataSetChanged();
                addAndMins(itemData.getIproductid(), "1");
                onPriceChange.priceChange();
            }
        });

        minsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float mincount=itemData.getMincount();
                if(mincount>1){}else{
                    mincount=1;
                }
                String number = text.getText().toString().trim();
                float n =  Float.parseFloat(number);

                if (n <= 1) {
                    n = mincount;
                } else {
                    n=n-mincount;
                    if(n==0){
                        n=mincount;
                    }
                    addAndMins(itemData.getIproductid(), "-1");
                }
                itemData.setInumber( n);
                notifyDataSetChanged();
                onPriceChange.priceChange();
            }
        });


        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomAlterDialog dialog=new CustomAlterDialog(contxt);
                dialog.setTitle("删除");
                dialog.setContent_text("确定要删除吗？");
                dialog.setCancelButton("取消");
                        dialog.setConfirmButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                                Call<BaseModel<?>> call = ApiInstant.INSTANCE.getInstant().deleteCart(AppApplication.Companion.getApptype(),
                                        AppApplication.Companion.getShopid(), itemData.getIproductid(), AppApplication.Companion.getToken());
                                call.enqueue(new ApiCallback<BaseModel<?>>() {
                                    @Override
                                    public void onSucssce(BaseModel baseModel) {
                                        onPriceChange.listChange();
                                        ToastUitl.showToast("删除成功");
                                    }

                                    @Override
                                    public void onFailure() {

                                    }
                                });
                            }
                        });

            }
        });

    }

    public interface OnPriceChange {
        public void priceChange();
        public void listChange();
    }

    private void addAndMins(String iproductid, String num){
        Call<BaseModel<?>> call = ApiInstant.INSTANCE.getInstant().addCart(AppApplication.Companion.getApptype(), AppApplication.Companion.getShopid(), num, iproductid, "", AppApplication.Companion.getToken());
        call.enqueue(new ApiCallback<BaseModel<?>>() {
            @Override
            public void onSucssce(BaseModel baseModel) {
            }
            @Override
            public void onFailure() {
            }
        });
    }
}
