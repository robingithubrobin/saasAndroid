package com.herotculb.qunhaichat.homeactiviti.goods.orderquery;

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
import com.herotculb.qunhaichat.dto.InOrderDto;
import com.herotculb.qunhaichat.homeactiviti.goods.query.chart.ChartGoods;
import com.herotculb.qunhaichat.view.listview.updown.XListView;
import com.herotculb.qunhaichat.view.listview.updown.XListView.IXListViewListener;
import com.herotculb.qunhaichat.widget.EditTextWithDate;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 
 * @author Administrator
 *
 */
public class QueryOrderWindow extends Activity {
	private Class classes;
	private String type;
	private boolean isinOrder;
	private boolean isNum;
	private String queryType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.query_order_window);
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		String classtype= b.getString("class");
		type= b.getString("type");
		queryType= b.getString("querytype");
		Log.e("-------------", classtype);
		try {
			Class clazz = Class.forName(classtype);
			classes=clazz;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(queryType.equals("inOrderQuery")){
			isinOrder=true;
		}else{
			isinOrder=false;
		}
		BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
		nowpage.setText("1");
		XListView view=(XListView) findViewById(R.id.queryGoods_list);
		view.setPullLoadEnable(true);
		view.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				EditTextWithDate startDate=(EditTextWithDate) findViewById(R.id.good_chart_startDate);
				EditTextWithDate endDate=(EditTextWithDate) findViewById(R.id.good_chart_endDate);
				BootstrapEditText orderNum=(BootstrapEditText) findViewById(R.id.query_order_num);
				BootstrapButton orderNumEnter=(BootstrapButton) findViewById(R.id.query_order_query_num_enter);
				BootstrapEditText orderName=(BootstrapEditText) findViewById(R.id.query_order_name);
				BootstrapButton orderNameEnter=(BootstrapButton) findViewById(R.id.query_order_query_enter);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				String num=orderNum.getText().toString();
				String name=orderName.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				if(nowpagenum<=1){
					nowpagenum=1;
				}else{
					nowpagenum=nowpagenum-1;
				}
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(QueryOrderWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryOrderHandler handler=new QueryOrderHandler(QueryOrderWindow.this,dialog,view,classes,QueryOrderWindow.this.type,isinOrder);
				QueryOrderThread thread=new QueryOrderThread(QueryOrderWindow.this, handler, name, num, nowpagenum+"", "10", endDate.getText().toString(), startDate.getText().toString(), isinOrder,isNum);
				thread.start();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				EditTextWithDate startDate=(EditTextWithDate) findViewById(R.id.good_chart_startDate);
				EditTextWithDate endDate=(EditTextWithDate) findViewById(R.id.good_chart_endDate);
				BootstrapEditText orderNum=(BootstrapEditText) findViewById(R.id.query_order_num);
				BootstrapButton orderNumEnter=(BootstrapButton) findViewById(R.id.query_order_query_num_enter);
				BootstrapEditText orderName=(BootstrapEditText) findViewById(R.id.query_order_name);
				BootstrapButton orderNameEnter=(BootstrapButton) findViewById(R.id.query_order_query_enter);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				String num=orderNum.getText().toString();
				String name=orderName.getText().toString();
				int nowpagenum=Integer.parseInt(nowpage.getText().toString());
				Log.e("-------------------", nowpagenum+"");
				nowpagenum=nowpagenum+1;
				nowpage.setText(String.valueOf(nowpagenum));
				LoadingDialog dialog = new LoadingDialog(QueryOrderWindow.this, "正在获取数据");
				dialog.show();
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryOrderHandler handler=new QueryOrderHandler(QueryOrderWindow.this,dialog,view,classes,QueryOrderWindow.this.type,isinOrder);
				QueryOrderThread thread=new QueryOrderThread(QueryOrderWindow.this, handler, name, num, nowpagenum+"", "10", endDate.getText().toString(), startDate.getText().toString(), isinOrder,isNum);
				thread.start();
			}
		});
		BootstrapButton queryNumEnter=(BootstrapButton)findViewById(R.id.query_order_query_num_enter);
		BootstrapButton querynameEnter=(BootstrapButton)findViewById(R.id.query_order_query_enter);
		queryNumEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditTextWithDate startDate=(EditTextWithDate) findViewById(R.id.good_chart_startDate);
				EditTextWithDate endDate=(EditTextWithDate) findViewById(R.id.good_chart_endDate);
				BootstrapEditText orderNum=(BootstrapEditText) findViewById(R.id.query_order_num);
				BootstrapButton orderNumEnter=(BootstrapButton) findViewById(R.id.query_order_query_num_enter);
				BootstrapEditText orderName=(BootstrapEditText) findViewById(R.id.query_order_name);
				BootstrapButton orderNameEnter=(BootstrapButton) findViewById(R.id.query_order_query_enter);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				String name=orderName.getText().toString();
				String num=orderNum.getText().toString();
				nowpage.setText("1");
				LoadingDialog dialog = new LoadingDialog(QueryOrderWindow.this, "正在获取数据");
				dialog.show();
				isNum=true;
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryOrderHandler handler=new QueryOrderHandler(QueryOrderWindow.this,dialog,view,classes,QueryOrderWindow.this.type,isinOrder);
				QueryOrderThread thread=new QueryOrderThread(QueryOrderWindow.this, handler, name, num, "1", "10", endDate.getText().toString(), startDate.getText().toString(), isinOrder,isNum);
				thread.start();
			}
		});
		querynameEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditTextWithDate startDate=(EditTextWithDate) findViewById(R.id.good_chart_startDate);
				EditTextWithDate endDate=(EditTextWithDate) findViewById(R.id.good_chart_endDate);
				BootstrapEditText orderNum=(BootstrapEditText) findViewById(R.id.query_order_num);
				BootstrapButton orderNumEnter=(BootstrapButton) findViewById(R.id.query_order_query_num_enter);
				BootstrapEditText orderName=(BootstrapEditText) findViewById(R.id.query_order_name);
				BootstrapButton orderNameEnter=(BootstrapButton) findViewById(R.id.query_order_query_enter);
				BootstrapEditText nowpage=(BootstrapEditText) findViewById(R.id.query_goods_nowpage);
				String name=orderName.getText().toString();
				String num=orderNum.getText().toString();
				nowpage.setText("1");
				LoadingDialog dialog = new LoadingDialog(QueryOrderWindow.this, "正在获取数据");
				dialog.show();
				isNum=false;
				XListView view=(XListView) findViewById(R.id.queryGoods_list);
				QueryOrderHandler handler=new QueryOrderHandler(QueryOrderWindow.this,dialog,view,classes,QueryOrderWindow.this.type,isinOrder);
				QueryOrderThread thread=new QueryOrderThread(QueryOrderWindow.this, handler, name, num, "1", "10", endDate.getText().toString(), startDate.getText().toString(), isinOrder,isNum);
				thread.start();
			}
		});
		BootstrapButton close=(BootstrapButton)findViewById(R.id.query_order_query_close);
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QueryOrderWindow.this, HomeActivity.class);  
		        Bundle b2 = new Bundle();
		        b2.putString("type", "cancel");
		        i.putExtras(b2);
		        setResult(RESULT_OK, i);  
		        finish();
			}
		});
	}
}
