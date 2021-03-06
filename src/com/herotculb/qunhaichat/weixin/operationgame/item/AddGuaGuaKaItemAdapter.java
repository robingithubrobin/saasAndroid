package com.herotculb.qunhaichat.weixin.operationgame.item;

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

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.herotculb.qunhaichat.HomeActivity;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.AwardsDto;
import com.herotculb.qunhaichat.weixin.operationgame.window.GameAddAwardsToGuaGuaKaQueryWindow;
import com.herotculb.qunhaichat.weixin.operationgame.window.GameAddAwardsToGuaGuaKaWindow;

public class AddGuaGuaKaItemAdapter extends BaseAdapter {
	private GameAddAwardsToGuaGuaKaQueryWindow  context;
	private List<AwardsDto> list;
	String type;
	
	public AddGuaGuaKaItemAdapter(GameAddAwardsToGuaGuaKaQueryWindow context, List<AwardsDto> list,
			String type) {
		super();
		this.context = context;
		this.list = list;
		this.type = type;
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
		final AwardsDto dto=(AwardsDto) getItem(position);
		LayoutInflater inflater = LayoutInflater.from(context);
		TableLayout gridLayout = (TableLayout) inflater.inflate(
				R.layout.weixin_game_add_guaguaka_item_layout, null);
		TextView text=(TextView) gridLayout.findViewById(R.id.weixin_game_guaguaka_layout_name);
		BootstrapButton update=(BootstrapButton) gridLayout.findViewById(R.id.weixin_game_guaguaka_layout_enter);
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = null;
				Bundle b2 = new Bundle();
				if(type.equals("game_add_awards")){
					i = new Intent(context, GameAddAwardsToGuaGuaKaWindow.class);  
			        b2.putString("type", "game_add_awards");
				}if(type.equals("game_dazhuanpan_content1")){
					i = new Intent(context, HomeActivity.class);
					b2.putString("type", "game_dazhuanpan_content1");
				}if(type.equals("game_dazhuanpan_content2")){
					i = new Intent(context, HomeActivity.class);
					b2.putString("type", "game_dazhuanpan_content2");
				}if(type.equals("game_dazhuanpan_content3")){
					i = new Intent(context, HomeActivity.class);
					b2.putString("type", "game_dazhuanpan_content3");
				}
				
		        b2.putLong("id", dto.getId());
		        b2.putString("content", dto.getContent());
		        i.putExtras(b2);
		        context.setResult( context.RESULT_OK, i);  
		        context.overridePendingTransition(R.anim.modal_in, R.anim.modal_out);
		        context.finish();
			}
		});
		text.setText(dto.getContent());
		return gridLayout;
	}

}
