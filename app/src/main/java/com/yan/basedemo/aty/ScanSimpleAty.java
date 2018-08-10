package com.yan.basedemo.aty;

import android.util.Log;

import com.google.zxing.Result;
import com.yan.base.BaseAty;
import com.yan.basedemo.R;

import butterknife.BindView;
import cn.earthyan.codescanner.ZXingScannerView;

/**
 * Created by YanZi on 2018/8/8.
 * describe：
 * modify:
 * modify date:
 */
public class ScanSimpleAty extends BaseAty implements ZXingScannerView.ResultHandler {

    @BindView(R.id.zx_view_scan)
    ZXingScannerView mScannerView;


    public static final String TAG = ScanSimpleAty.class.getName();

    @Override
    protected int setContentLayout() {
        return R.layout.aty_scan;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
