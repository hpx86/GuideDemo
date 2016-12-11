package com.onlybeyond.guidedemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import static com.onlybeyond.guidedemo.utils.LogUtils.makeLogTag;

/**
 * Created by only on 16/6/14.
 */
public abstract class BaseFragment extends Fragment  {
    private static String TAG = makeLogTag(BaseFragment.class);

    //state
    private boolean isHandlerNetworkError = true;//是否在基类处理网络异常


    //view
    Toast mToast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = initView(inflater, container, savedInstanceState);
        if (savedInstanceState != null) {
            restore(savedInstanceState);
        } else {
            initData();
        }
        fillData();
        requestData();


        return ret;

    }

    /**
     * restore data from savedInstanceState
     */
    public void restore(Bundle savedInstanceState) {
    }

    /**
     * init  get query from other page
     */
    public void initData() {
    }

    /**
     * find view from layout and set listener
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * init data
     */
    public abstract void fillData();

    /**
     * network request
     */
    public abstract void requestData();


    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity(), text,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void showToast(final int resId) {
        if (resId != 0
                && getActivity() != null
                && !getActivity().isFinishing()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getActivity(), resId,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(resId);
                    }
                    mToast.show();
                }
            });
        }

    }




}
