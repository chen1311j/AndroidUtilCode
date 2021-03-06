package com.blankj.androidutilcode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.androidutilcode.R;
import com.blankj.androidutilcode.activity.StatusBarFragmentActivity;
import com.blankj.androidutilcode.base.BaseFragment;
import com.blankj.utilcode.util.BarUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/07/01
 *     desc  :
 * </pre>
 */
public class StatusBarAlphaFragment extends BaseFragment<StatusBarFragmentActivity> {

    private int mAlpha;

    private TextView mTvStatusAlpha;
    private SeekBar  sbChangeAlpha;
    private View     fakeStatusBar;


    public static StatusBarAlphaFragment newInstance() {
        return new StatusBarAlphaFragment();
    }

    @Override
    public void initData(Bundle bundle) {
        mAlpha = 112;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_status_bar_alpha;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        fakeStatusBar = view.findViewById(R.id.fake_status_bar);
        mTvStatusAlpha = (TextView) view.findViewById(R.id.tv_status_alpha);
        sbChangeAlpha = (SeekBar) view.findViewById(R.id.sb_change_alpha);
        view.findViewById(R.id.btn_set_transparent).setOnClickListener(this);
        sbChangeAlpha.setOnSeekBarChangeListener(translucentListener);
        mTvStatusAlpha.setText(String.valueOf(mAlpha));
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_transparent:
                sbChangeAlpha.setProgress(0);
                break;
        }
    }

    private SeekBar.OnSeekBarChangeListener translucentListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mAlpha = progress;
            mTvStatusAlpha.setText(String.valueOf(mAlpha));
            updateFakeStatusBar();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void updateFakeStatusBar() {
        BarUtils.setFakeStatusBarAlpha(fakeStatusBar, mAlpha);
    }
}
