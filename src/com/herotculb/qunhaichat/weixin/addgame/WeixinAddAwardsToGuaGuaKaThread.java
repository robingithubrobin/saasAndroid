package com.herotculb.qunhaichat.weixin.addgame;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class WeixinAddAwardsToGuaGuaKaThread extends Thread {
	private Activity context;
	private Handler handler;
	
	public WeixinAddAwardsToGuaGuaKaThread(Activity context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		
		BootstrapEditText texttext=(BootstrapEditText) context.findViewById(R.id.weixin_add_guaguaka_text);
		BootstrapEditText idtext=(BootstrapEditText) context.findViewById(R.id.weixin_add_guaguaka_id);
		BootstrapEditText starttext=(BootstrapEditText) context.findViewById(R.id.weixin_add_guaguaka_start_date);
		BootstrapEditText endtext=(BootstrapEditText) context.findViewById(R.id.weixin_add_guaguaka_end_date);
		BootstrapEditText numtext=(BootstrapEditText) context.findViewById(R.id.weixin_add_guaguaka_num);
		
		String id = idtext.getText().toString();
		String text = texttext.getText().toString();
		String startdate = starttext.getText().toString();
		String enddate = endtext.getText().toString();
		String num = numtext.getText().toString();
		
		Log.e("id---", id);
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list= qnpAPi.addAwardsToguaguaka("0", text, id, enddate, startdate, num);
		Map<String,Object> map=list.iterator().next();
		Message sendmsg = Message.obtain();  
        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
        handler.sendMessage(sendmsg); 
	}
	
}
