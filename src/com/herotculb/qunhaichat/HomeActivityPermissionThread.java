package com.herotculb.qunhaichat;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class HomeActivityPermissionThread extends Thread{
		Context content;
		Handler uihandler;
		public HomeActivityPermissionThread(Handler handler,Context content){
			uihandler=handler;
			this.content=content;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			Looper.prepare();
			QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(content);
			List<Map<String, Object>> list=qnpAPi.backManagerajax();
			Map<String,Object> map=list.iterator().next();
			Message sendmsg = Message.obtain();	
	        sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
	        uihandler.sendMessage(sendmsg);
		}

}
