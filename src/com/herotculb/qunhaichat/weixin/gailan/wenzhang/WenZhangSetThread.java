package com.herotculb.qunhaichat.weixin.gailan.wenzhang;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.herotculb.qunhaichat.util.QNPermissionApiImpl;

public class WenZhangSetThread extends Thread{
	private Context context;
	private String name;
	private Handler handler;
	private String nowpage;
	private String countNum;
		
	public WenZhangSetThread(Context context, String name,String nowpage,String countNum,Handler handler) {
		super();
		this.context = context;
		this.name = name;
		this.handler = handler;
		this.countNum=countNum;
		this.nowpage=nowpage;
	}
	@Override
	public void run() {
		Looper.prepare();
		QNPermissionApiImpl qnpAPi=new QNPermissionApiImpl(context);
		List<Map<String, Object>> list=qnpAPi.getWenzhangList(name,nowpage,countNum);
		Map<String,Object> map=list.iterator().next();
		boolean b=(Boolean) map.get("success");
		if(b){
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            handler.sendMessage(sendmsg); 
		}else{
			//获取数据失败
			Message sendmsg = Message.obtain();  
            sendmsg.obj = map;   //利用Message.obj把子线程的handle传递给主线程。  
            handler.sendMessage(sendmsg);  
		}
	}
}
