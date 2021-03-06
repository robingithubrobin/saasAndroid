package com.herotculb.qunhaichat.weixin.gailan.huifushezhi.adapter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.WeiXinAutoReSendMenuDto;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.AutoResendSetDeleteHandler;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.AutoResendSetDeleteThread;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.AutoResendSetOpenHandler;
import com.herotculb.qunhaichat.weixin.gailan.huifushezhi.AutoResendSetOpenThread;
import com.herotculb.qunhaichat.weixin.gailan.window.AutoResendSetItemWindow;
import com.herotculb.qunhaichat.weixin.gailan.window.AutoResendSetWindow;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class AutoResendSetAdapter extends BaseAdapter{
	private HomeActivity  context;
	private List<WeiXinAutoReSendMenuDto> list;
	public AutoResendSetAdapter(HomeActivity context,List<WeiXinAutoReSendMenuDto> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final WeiXinAutoReSendMenuDto dto=(WeiXinAutoReSendMenuDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.weixin_gailan_autoresouresetitem_layout, null);
		TextView text=(TextView) gridLayout.findViewById(R.id.weixin_gailan_autoresouresetitem_name);
		BootstrapButton update=(BootstrapButton) gridLayout.findViewById(R.id.weixin_gailan_autoresouresetitem_update);
		BootstrapButton delete=(BootstrapButton) gridLayout.findViewById(R.id.weixin_gailan_autoresouresetitem_delete);
		BootstrapButton additem=(BootstrapButton) gridLayout.findViewById(R.id.weixin_gailan_autoresouresetitem_additem);
		BootstrapButton open=(BootstrapButton) gridLayout.findViewById(R.id.weixin_gailan_autoresouresetitem_open);
		if(dto.isUses()){
			open.setText("关闭");
		}else{
			open.setText("打开");
		}
		if(dto.getName().equals("null")){
			text.setText("");
		}else{
			text.setText(dto.getName());
		}
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, AutoResendSetWindow.class);  
				Bundle b = new Bundle();
				if(dto.getType()==dto.TYPE_EVENT){

				b.putInt("type",dto.TYPE_EVENT);
				b.putString("event_type",dto.getWeixin_events());
				b.putString("key", String.valueOf(dto.getWeixin_keys()));
				}else{
					b.putInt("type",dto.getType());
				}
				b.putString("id",String.valueOf(dto.getId()));
				b.putString("content", dto.getContent());
				b.putString("name", dto.getName());
                i.putExtras(b); 
                context.startActivityForResult(i, 0);  
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("删除")
                .setContentText("您确定删除此信息？")
                .setCancelText("取消")
                .setConfirmText("确定")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("取消删除")
                                .setContentText("您的删除已经取消")
                                .setConfirmText("确定")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                    	LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
						dialog.show();
						AutoResendSetDeleteHandler handler=new AutoResendSetDeleteHandler(context,dto.getType(),dialog,sDialog);
						AutoResendSetDeleteThread thread=new AutoResendSetDeleteThread(context, dto.getId(), handler);
                    	thread.start();
                    }
                })
                .show();
			}
		});
		additem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(context, AutoResendSetItemWindow.class);  
				Bundle b = new Bundle();
				b.putLong("id",dto.getId());
                i.putExtras(b); 
                context.startActivityForResult(i, 0);  
                context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
			}
		});
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoadingDialog dialog = new LoadingDialog(context, "正在获取数据");
				dialog.show();
				AutoResendSetOpenHandler handler=new AutoResendSetOpenHandler(context,dto.getType(),dialog);
				AutoResendSetOpenThread thread=new AutoResendSetOpenThread(context, dto.getType(), handler, dto);
            	thread.start();
			}
		});
		return gridLayout;
	}

}
