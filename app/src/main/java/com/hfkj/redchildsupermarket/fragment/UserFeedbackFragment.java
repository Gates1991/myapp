package com.hfkj.redchildsupermarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hfkj.redchildsupermarket.R;

/**
 * @创建者 Shayne
 * @创建时间 2016/9/6 18:27
 * @描述着 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserFeedbackFragment extends  BaseFragment implements View.OnClickListener {


    private View mView;
    private Button mBt_title_left;
    private Button mBt_title_right;
    private EditText mEt_feedback;
    private EditText mEt_tel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstnceState) {
        mView = inflater.inflate(R.layout.fragment_userfeedback, null);
        mMainActivity.isMainFrament = 2;
        initTitleView();
        initData();
        return mView;
    }



    private void initTitleView() {
        mBt_title_left = (Button) mView.findViewById(R.id.bt_title_left);
        mBt_title_left.setVisibility(View.VISIBLE);

        mBt_title_right = (Button) mView.findViewById(R.id.bt_title_right);

        mBt_title_right.setVisibility(View.VISIBLE);
        mBt_title_right.setText("");

        TextView mTv_title_name = (TextView) mView.findViewById(R.id.tv_title_name);
        mTv_title_name.setText("用户反馈");

        mEt_feedback = (EditText) mView.findViewById(R.id.et_feedback);
        mEt_tel = (EditText) mView.findViewById(R.id.et_tel);


        mBt_title_left.setOnClickListener(this);
        mBt_title_right.setOnClickListener(this);

    }

    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_title_left:
                mMainActivity.popBackStack();
                break;
            case R.id.bt_title_right:
                if (mEt_feedback.getText().toString().isEmpty()&&mEt_tel.getText().toString().isEmpty()) {

                    Toast.makeText(mContext, "评价或者联系方式不能为空", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }
}
