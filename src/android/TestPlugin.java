package com.jinan.firsttest;

import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class TestPlugin extends CordovaPlugin {
    
    private static final String R_SUCCESS = "success";
    private static final String R_FAIL = "fail";
    private static final String R_CANCEL = "cancel";
    private String mTN;
    private Thread mThread;
    private String resultStr;
    private CallbackContext callbackContext;
    
    //    private Handler mHandler = new Handler() {
    //        @Override
    //        public void handleMessage(Message msg) {
    //            super.handleMessage(msg);
    //            String tn = (String) msg.obj;
    //            mTN = tn;
    //            mThread = null;
    //            Toast.makeText(cordova.getActivity(), "已经获取了流水号:" + mTN, Toast.LENGTH_SHORT).show();
    //        }
    //    };
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("pay")) {
            String message = args.getString(0);
            // getTn();
            //            mTN="715529982582411317101";
            // pay();
            
            this.coolMethod(message, callbackContext);
            
            return true;
        }
        return false;
    }
    
    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            // callbackContext.success(resultStr);
            // pay();
            Intent intent = new Intent(this.cordova.getActivity(), TestActivity.class);
            intent.putExtra("mTn",message);
            this.cordova.startActivityForResult((CordovaPlugin) this, intent, 0x001);
            
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    
    
    //    private boolean verify(String sign) {
    //        return true;
    //    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //         if (resultCode==RESULT_OK){
        switch (requestCode) {
            case 0x001:
                String str = data.getStringExtra("result");
                PluginResult mPlugin = new PluginResult(PluginResult.Status.OK,
                                                        str);
                mPlugin.setKeepCallback(true);
                callbackContext.sendPluginResult(mPlugin);
                // Toast.makeText(this,str,Toast.LENGTH_LONG).show();
                break;
        }
        //        }
        //         if (data == null) {
        //             return;
        //         }
        //         resultStr="无响应";
        //         String msg = "";
        //         String str = data.getExtras().getString("pay_result");
        //         if (str.equalsIgnoreCase(R_SUCCESS)) {
        //             // 如果想对结果数据验签，可使用下面这段代码，
        //             // 但建议不验签，直接去商户后台查询交易结果
        //             // result_data结构见result_data参数说明
        
        //             // 结果result_data为成功时，去商户后台查询一下再展示成功
        //             if (data.hasExtra("result_data")) {
        //                 String sign = data.getExtras().getString("result_data");
        //                 // 此处的verify建议送去商户后台做验签
        //                 // 如要放在手机端验，则代码必须支持更新证书
        //                 if (verify(sign)) {
        //                     //验签成功，显示支付结果
        //                     resultStr="支付成功！";
        //                     Toast.makeText(cordova.getActivity(), " 支付成功！ ", Toast.LENGTH_SHORT).show();
        //                 } else {
        //                     // 验签失败
        //                 }
        //             }
        
        //         } else if (str.equalsIgnoreCase(R_FAIL)) {
        //             resultStr="支付失败！";
        //             Log.e("x",""+resultStr);
        //             Toast.makeText(cordova.getActivity(), " 支付失败！ ", Toast.LENGTH_SHORT).show();
        
        //         } else if (str.equalsIgnoreCase(R_CANCEL)) {
        //             resultStr="你已取消了本次订单的支付！";
        //             Toast.makeText(cordova.getActivity(), " 你已取消了本次订单的支付！ ", Toast.LENGTH_SHORT).show();
        //         }
        // PluginResult mPlugin = new PluginResult(PluginResult.Status.OK,
        //                         resultStr);
        //                 mPlugin.setKeepCallback(true);
        //                 callbackContext.sendPluginResult(mPlugin);
        
    }
    
    
    //        public void pay() {
    //        if (mTN == null) {
    //            Toast.makeText(cordova.getActivity(), "先获取流水号", Toast.LENGTH_SHORT).show();
    //            return;
    //        }
    //        String serverMode = "01";
    //        UPPayAssistEx.startPay(cordova.getActivity(), null, null, mTN, serverMode);
    //        // mTN="1";
    //        // UPPayAssistEx.startPay(cordova.getActivity(), null, null, "715529982582411317101", serverMode);
    //        //  mTN="715529982582411317101";
    //
    //    }
    
    //  @Override
    // protected void onDestroy() {
    //     super.onDestroy();
    //     if (mThread != null) {
    //         mThread.interrupt();
    //     }
    // }
    
    //    public void getTn() {
    //        if (mThread != null) {
    //            // Toast.makeText(this, "  正在获取", Toast.LENGTH_SHORT).show();
    //            return;
    //        }
    //        mThread = new Thread(new Runnable() {
    //            @Override
    //            public void run() {
    //                URL url = null;
    //                try {
    //                    url = new URL("http://101.231.204.84:8091/sim/getacptn");
    //                    URLConnection rulConnection = url.openConnection();// 此处的urlConnection对象实际上是根据URL的
    //                    HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
    //                    httpUrlConnection.setRequestMethod("GET");
    //                    httpUrlConnection.connect();
    //                    InputStream inputStream = httpUrlConnection.getInputStream();
    //                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    //                    byte[] buffer = new byte[2048];
    //                    int len = bufferedInputStream.read(buffer);
    //                    String tn = new String(buffer, 0, len);
    //                    mHandler.obtainMessage(0, tn).sendToTarget();
    //                } catch (MalformedURLException e) {
    //                    e.printStackTrace();
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //
    //            }
    //        });
    //        mThread.start();
    //    }
}
