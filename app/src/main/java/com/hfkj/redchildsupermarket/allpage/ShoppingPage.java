package com.hfkj.redchildsupermarket.allpage;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.hfkj.redchildsupermarket.R;
import com.hfkj.redchildsupermarket.activity.MainActivity;

/**
 * Created by wf on 2016/9/5.
 */
public class ShoppingPage extends BasePage {

    private ImageButton mIv_shop_view;

    public ShoppingPage(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.shopping_page, null);
        mIv_shop_view = (ImageButton) view.findViewById(R.id.ib_shop_view);
        return view;
    }

    @Override
    public void initData() {
        mIv_shop_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到品牌页面
                ((MainActivity) mContext).getRadioButton().setChecked(true);
            }
        });
    }

}
