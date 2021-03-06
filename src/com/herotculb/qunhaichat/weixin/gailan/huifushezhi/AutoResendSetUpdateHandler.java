package com.herotculb.qunhaichat.weixin.gailan.huifushezhi;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.weixin.gailan.window.AutoResendSetWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetUpdateHandler extends Handler {
	AutoResendSetWindow context;
	ListView view;
	LoadingDialog dialog;
	int type;
	SweetAlertDialog dialog2;
	
	public AutoResendSetUpdateHandler(AutoResendSetWindow autoResendSetWindow,int typestr, 
			LoadingDialog dialog) {
		super();
		this.context = autoResendSetWindow;
		this.view = view;
		this.dialog = dialog;
		this.type=typestr;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		if(b){
			
			Intent i = new Intent(context, HomeActivity.class);  
	        Bundle b2 = new Bundle();
	        b2.putString("type", "weixin_gailan_AutoResendsetAddHandler");
	        b2.putInt("weixin_gailan_AutoResendsetAddHandlertype", type);
	        i.putExtras(b2);
	        context.setResult(context.RESULT_OK, i);  
	        context.finish();  
		}else{
			new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("删除")
             .setContentText(String.valueOf(map.get("info")))
             .setConfirmText("确定")
             .showCancelButton(false)
             .setCancelClickListener(null)
             .setConfirmClickListener(null).show();
		}
	}
}
