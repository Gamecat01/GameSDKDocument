package com.youximao.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.gamecat.pay.GameCatSDK;
import com.gamecat.pay.GameCatSDKListener;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity implements View.OnClickListener {
    Activity activity;

    Button login;
    Button order;
    Button center;
    TextView listener;
    EditText payEdit;
    Button environment;
    boolean environmentNo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.main);
        activity=this;
        //0为联调环境，1为正式环境,2为测试，3为开发
        GameCatSDK.setEnvironment(this,2,"eW91eGltYW83MDAwMDFsdA==");
        GameCatSDK.sdkCancelListener(new GameCatSDKListener() {
            @Override
            public void onSuccess(final JSONObject message) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GameCatSDK.Login(activity,"test_xianjian",new GameCatSDKListener() {
                            @Override
                            public void onSuccess(JSONObject message) {
                                try {
                                    String openId= message.getString("openId");
                                    //取得用户的唯一标识进行游戏的登陆
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                listener.setText("登陆成功回调参数"+message+"");
                                Log.e("返回数据",message+"");

                            }

                            @Override
                            public void onFail(String message) {
                                listener.setText(message+"");
                            }
                        });
                    }
                });

            }

            @Override
            public void onFail(String message) {

            }
        });
        activity=this;
        listener=(TextView)findViewById(R.id.listener_text);
        listener.setText("内部环境");
        login=(Button)findViewById(R.id.login);
        order=(Button)findViewById(R.id.order);
        center=(Button)findViewById(R.id.center);
        login.setOnClickListener(this);
        order.setOnClickListener(this);
        center.setOnClickListener(this);
        payEdit=(EditText)findViewById(R.id.pay_edit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login:


                // Log.e("加密后的数据：",DESUtil.encryptBasedDes("游戏猫"));
                String gameId="test_xianjian";
                /**
                 * 登陆
                 * @param listener 登陆监听器，用作登陆成功或者失败的回调
                 * @param gameId 游戏Id
                 */
                listener.setText("上送游戏Id"+gameId);
                GameCatSDK.Login(activity,gameId,new GameCatSDKListener() {
                    @Override
                    public void onSuccess(JSONObject message) {
                        try {
                            String openId= message.getString("openId");
                            //取得用户的唯一标识进行游戏的登陆
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.setText("登陆成功回调参数"+message+"");
                        Log.e("返回数据",message+"");

                    }

                    @Override
                    public void onFail(String message) {
                        listener.setText(message+"");
                    }
                });
                break;
            case R.id.order:

                double amount=0;
                if(!"".equals(payEdit.getText().toString())){
                    amount=Double.parseDouble(payEdit.getText().toString());
                }else{
                    amount=0.1;
                }
                String cpDiscript="倚天剑";
                String codeNo=String.valueOf(System.currentTimeMillis());
                String notifyUrl="http://mock.youximao.cn/mockjsdata/11/sdk/notify";
                String extend="一区张无忌下的订单";

                /**
                 * 调用订单方法
                 * @param amount 订单金额
                 * @param cpDiscript 产品介绍
                 * @param codeNo 订单编号
                 * @param notifyUrl 发货回调地址
                 * @param extend 扩展参数
                 * @param listener 订单回调
                 */
                listener.setText("上送游戏订单"+"金额"+amount+";产品介绍"+cpDiscript+";订单编号"+codeNo+";发货回调地址"+notifyUrl+";扩展参数"+extend);
                GameCatSDK.Order(activity, amount, cpDiscript, codeNo, notifyUrl,null, new GameCatSDKListener() {
                    @Override
                    public void onSuccess(JSONObject message) {
                        listener.setText(message + "");
                    }

                    @Override
                    public void onFail(String message) {
                        listener.setText(message + "");
                    }
                });
                break;
            case R.id.center:
                listener.setText("点击了用户中心");
                GameCatSDK.UserCenter(activity,new GameCatSDKListener() {
                    @Override
                    public void onSuccess(JSONObject message) {
                        listener.setText(message+"");
                    }

                    @Override
                    public void onFail(String message) {
                        listener.setText(message+"");
                    }
                });
                break;



            default:

                break;

        }

    }





}
