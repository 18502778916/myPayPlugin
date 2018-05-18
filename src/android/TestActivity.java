package com.jinan.firsttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.unionpay.UPPayAssistEx;

public class TestActivity extends Activity {

    private static final String R_SUCCESS = "success";
    private static final String R_FAIL = "fail";
    private static final String R_CANCEL = "cancel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        pay();
    }

    private void pay(){
        String serverMode = "01";
        String mTN=getIntent().getStringExtra("mTN"); 
        UPPayAssistEx.startPay(this, null, null, mTN, serverMode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String msg = "";
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase(R_SUCCESS)) {
            // 如果想对结果数据验签，可使用下面这段代码，
            // 但建议不验签，直接去商户后台查询交易结果
            // result_data结构见result_data参数说明

            // 结果result_data为成功时，去商户后台查询一下再展示成功
            if (data.hasExtra("result_data")) {
                String sign = data.getExtras().getString("result_data");
                // 此处的verify建议送去商户后台做验签
                // 如要放在手机端验，则代码必须支持更新证书
                if (verify(sign)) {
                    //验签成功，显示支付结果
                    Toast.makeText(this, " 支付成功！ ", Toast.LENGTH_SHORT).show();
                } else {
                    // 验签失败
                }
            }

        } else if (str.equalsIgnoreCase(R_FAIL)) {
//            Toast.makeText(this, " 支付失败！ ", Toast.LENGTH_SHORT).show();

        } else if (str.equalsIgnoreCase(R_CANCEL)) {
            Toast.makeText(this, " 你已取消了本次订单的支付！ ", Toast.LENGTH_SHORT).show();
        }
        Intent intent=new Intent();
        intent.putExtra("result","支付失败！");
        setResult(RESULT_OK,intent);
        finish();
    }

    private boolean verify(String sign) {
        return true;
    }
}
