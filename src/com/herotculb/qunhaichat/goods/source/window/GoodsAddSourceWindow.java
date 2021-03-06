package com.herotculb.qunhaichat.goods.source.window;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.goods.operationsource.GoodsAddSourceHandler;
import com.herotculb.qunhaichat.goods.operationsource.GoodsAddSourceThread;
import com.herotculb.qunhaichat.goods.operationsource.GoodsUpdateSourceHandler;
import com.herotculb.qunhaichat.goods.operationsource.GoodsUpdateSourceThread;
import com.herotculb.qunhaichat.widget.LoadingDialog;
/**
 * 进货商信息操作-添加新供货商联系人
 * @author lkx
 *
 */
public class GoodsAddSourceWindow extends Activity {
	private BootstrapButton enter;
	private BootstrapButton calcel;
	private BootstrapEditText id;
	private BootstrapEditText name;
	private BootstrapEditText address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏头
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.goods_add_source_window);
		enter =(BootstrapButton)findViewById(R.id.goods_source_content_enter);
		calcel = (BootstrapButton)findViewById(R.id.goods_source_content_cancel);
		id = (BootstrapEditText)findViewById(R.id.goods_source_id);
		name = (BootstrapEditText)findViewById(R.id.goods_source_name);
		address = (BootstrapEditText)findViewById(R.id.goods_source_address);
		Bundle bundle = getIntent().getExtras();
		Object idstr = (Object) bundle.get("id");
		Object namestr = (Object) bundle.get("name");
		Object addressstr = (Object) bundle.get("address");
		if(idstr!=null&&!idstr.equals("")){
			id.setText(idstr.toString());
			name.setText(namestr.toString());
			address.setText(addressstr.toString());
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					LoadingDialog dialog2 = new LoadingDialog(
							GoodsAddSourceWindow.this, "正在获取数据");
					dialog2.show();
					GoodsUpdateSourceHandler handler = 
							new GoodsUpdateSourceHandler(GoodsAddSourceWindow.this, dialog2);
					GoodsUpdateSourceThread thread = 
							new GoodsUpdateSourceThread(GoodsAddSourceWindow.this, handler);
					thread.start();
				}
			});
		}else{
			enter.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					LoadingDialog dialog2 = new LoadingDialog(
							GoodsAddSourceWindow.this, "正在获取数据");
					dialog2.show();
					GoodsAddSourceHandler handler = 
							new GoodsAddSourceHandler(GoodsAddSourceWindow.this, dialog2);
					GoodsAddSourceThread thread = 
							new GoodsAddSourceThread(GoodsAddSourceWindow.this, handler);
					thread.start();
				}
			});
		}
		
		calcel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				GoodsAddSourceWindow.this.finish();
			}
		});
	}
	
}
