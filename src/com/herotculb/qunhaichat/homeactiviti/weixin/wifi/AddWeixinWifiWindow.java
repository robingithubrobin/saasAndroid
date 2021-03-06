package com.herotculb.qunhaichat.homeactiviti.weixin.wifi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AddWeixinWifiWindow extends Activity{
	private String id;
	private String dirces="true";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.weixin_wifi_add_layout);
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		String id=b.getString("id");
		this.id=id;
		BootstrapEditText name=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name);
		BootstrapEditText tokens=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name_token);
		BootstrapButton diceclose=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_dirce_close);
		BootstrapButton enter=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_enter);
		BootstrapButton centel=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_centel);
		if(id!=null){
			
			//是修改
			String names=b.getString("name");
			String tokenses=b.getString("token");
			
			dirces=b.getString("dirce");
			Log.e("-------", dirces);
			if(dirces.equals("true")){
				Log.e("------------------", "执行了");
				diceclose.setText("开启路由设备");
				dirces="false";
			}else{
				Log.e("------------------", "执行了");
				diceclose.setText("关闭路由设备");
				dirces="true";
			}
			String rigisters=b.getString("rigister");
			name.setText(names);
			tokens.setText(tokenses);
			diceclose.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					BootstrapButton diceclose=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_dirce_close);
					if(dirces.equals("true")){
						Log.e("------------------", "执行了");
						diceclose.setText("开启路由设备");
						dirces="false";
					}else{
						Log.e("------------------", "执行了");
						diceclose.setText("关闭路由设备");
						dirces="true";
					}
				}
			});
			
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					BootstrapEditText name=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name);
					BootstrapEditText tokens=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name_token);
					BootstrapButton diceclose=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_dirce_close);
					String namestr=name.getText().toString();
					String tokenses=tokens.getText().toString();
					String close=dirces;
					LoadingDialog dialog = new LoadingDialog(AddWeixinWifiWindow.this, "正在获取数据");
					dialog.show();
					AddWifiHandler handler=new AddWifiHandler(AddWeixinWifiWindow.this, dialog);
					AddWifiThread thread=new AddWifiThread(AddWeixinWifiWindow.this, handler,AddWeixinWifiWindow.this.id,namestr,tokenses,close);
					thread.start();
				}
			});
		}else{
			//是添加
			diceclose.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					BootstrapButton diceclose=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_dirce_close);
					
					if(dirces.equals("true")){
						Log.e("------------------", "执行了");
						diceclose.setText("开启路由设备");
						dirces="false";
					}else{
						Log.e("------------------", "执行了");
						diceclose.setText("关闭路由设备");
						dirces="true";
					}
				}
			});
			enter.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					BootstrapEditText name=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name);
					BootstrapEditText tokens=(BootstrapEditText) findViewById(R.id.weixin_wifi_add_layout_name_token);
					BootstrapButton diceclose=(BootstrapButton) findViewById(R.id.weixin_wifi_add_layout_dirce_close);
					String namestr=name.getText().toString();
					String tokenses=tokens.getText().toString();
					String close=dirces;
					LoadingDialog dialog = new LoadingDialog(AddWeixinWifiWindow.this, "正在获取数据");
					dialog.show();
					AddWifiHandler handler=new AddWifiHandler(AddWeixinWifiWindow.this, dialog);
					AddWifiThread thread=new AddWifiThread(AddWeixinWifiWindow.this, handler,"0",namestr,tokenses,close);
					thread.start();
				}
			});
			
		}
		centel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AddWeixinWifiWindow.this, HomeActivity.class);  
		        Bundle b2 = new Bundle();
		        b2.putString("type", "cancel");
		        i.putExtras(b2);
		        setResult(RESULT_OK, i);  
		        finish();
			}
		});
	}
}