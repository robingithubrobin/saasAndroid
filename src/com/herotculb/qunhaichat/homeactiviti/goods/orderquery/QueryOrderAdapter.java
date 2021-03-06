package com.herotculb.qunhaichat.homeactiviti.goods.orderquery;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.GoodsTableDto;
import com.herotculb.qunhaichat.dto.InOrderDto;
import com.herotculb.qunhaichat.dto.OrdersDto;
import com.herotculb.qunhaichat.dto.WeiXinGoodsDto;
import com.herotculb.qunhaichat.goods.store.window.GoodsAddStoreHouseWindow;
import com.herotculb.qunhaichat.util.DateUtil;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class QueryOrderAdapter extends BaseAdapter {
	private Activity context;
	private List list;
	private Class returnclass;
	private String type;
	private boolean isinOrder;

	public QueryOrderAdapter(Activity context, List list,
			Class returnclass, String type, boolean isinOrder) {
		this.context = context;
		this.list = list;
		this.returnclass = returnclass;
		this.type = type;
		this.isinOrder = isinOrder;
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
		
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = null;
		if (isinOrder) {
			gridLayout = (TableLayout) inflater.inflate(
					R.layout.query_inorder_window_item, null);
			final InOrderDto dto = (InOrderDto) getItem(position);
			TextView id = (TextView) gridLayout
					.findViewById(R.id.inorder_id);
			TextView name = (TextView) gridLayout
					.findViewById(R.id.inorder_name);
			TextView stute = (TextView) gridLayout
					.findViewById(R.id.inorder_stute);
			TextView createDate = (TextView) gridLayout
					.findViewById(R.id.inorder_createDate);
			TextView createMan = (TextView) gridLayout
					.findViewById(R.id.inorder_createMan);
			TextView rukuman = (TextView) gridLayout
					.findViewById(R.id.inorder_rukuMan);
			BootstrapButton look = (BootstrapButton) gridLayout
					.findViewById(R.id.inorder_look);
			BootstrapButton delete = (BootstrapButton) gridLayout
					.findViewById(R.id.inorder_delete);
			id.setText(dto.getId()+"");
			name.setText(dto.getName());
			stute.setText(dto.getState()==0?"未入库":"已入库");
			createDate.setText("日期:"+DateUtil.formatDateYYYY_MM_DD(new Date(Long.parseLong(dto.getCreateDate()))));
			createMan.setText("创建人:"+dto.getCreateUserName());
			rukuman.setText("入库人:"+dto.getInStoreUserName());
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog = new LoadingDialog(QueryOrderAdapter.this.context, "正在获取数据");
					dialog.show();
					DeleteOrderItemHandler handler=new DeleteOrderItemHandler(QueryOrderAdapter.this.context, dialog, isinOrder);
					DeleteOrderItemThread thread=new DeleteOrderItemThread(QueryOrderAdapter.this.context, handler, isinOrder, dto.getId()+"");
					thread.start();
				}
			});
			look.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(context,
							returnclass);
					Bundle b2 = new Bundle();
					b2.putString("type", "orderselect");
					b2.putString("type2",type);
					b2.putString("id",dto.getId()+"");
					i.putExtras(b2);
					context.setResult(context.RESULT_OK, i);
					context.finish();
				}
			});
			if(dto.getState()!=0){
				delete.setVisibility(View.GONE);
			}else{
					
			}
		} else {
			gridLayout = (TableLayout) inflater.inflate(
					R.layout.query_order_window_item, null);
			final OrdersDto dto = (OrdersDto) getItem(position);
			TextView id = (TextView) gridLayout
					.findViewById(R.id.inorder_id);
			TextView name = (TextView) gridLayout
					.findViewById(R.id.inorder_name);
			TextView stute = (TextView) gridLayout
					.findViewById(R.id.inorder_stute);
			TextView createDate = (TextView) gridLayout
					.findViewById(R.id.inorder_createDate);
			TextView createMan = (TextView) gridLayout
					.findViewById(R.id.inorder_createMan);
			TextView rukuman = (TextView) gridLayout
					.findViewById(R.id.inorder_rukuMan);
			BootstrapButton look = (BootstrapButton) gridLayout
					.findViewById(R.id.inorder_look);
			BootstrapButton delete = (BootstrapButton) gridLayout
					.findViewById(R.id.inorder_delete);
			id.setText(dto.getId()+"");
			name.setText(dto.getTitle());
			stute.setText(dto.getState()==0?"未入库":"已入库");
			createDate.setText("日期:"+DateUtil.formatDateYYYY_MM_DD(new Date(Long.parseLong(dto.getCreateDate()))));
			createMan.setText("创建人:"+dto.getCreateUserName());
			rukuman.setText("入库人:"+dto.getInStoreUserName());
			delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LoadingDialog dialog = new LoadingDialog(QueryOrderAdapter.this.context, "正在获取数据");
					dialog.show();
					DeleteOrderItemHandler handler=new DeleteOrderItemHandler(QueryOrderAdapter.this.context, dialog, isinOrder);
					DeleteOrderItemThread thread=new DeleteOrderItemThread(QueryOrderAdapter.this.context, handler, isinOrder, dto.getId()+"");
					thread.start();
				}
			});
			look.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(context,
							returnclass);
					Bundle b2 = new Bundle();
					b2.putString("type", "orderselect");
					b2.putString("type2",type);
					b2.putString("id",dto.getId()+"");
					i.putExtras(b2);
					context.setResult(context.RESULT_OK, i);
					context.finish();
				}
			});
			if(dto.getState()!=0){
				delete.setVisibility(View.GONE);
			}else{
					
			}
		}
		

		return gridLayout;
	}

}
