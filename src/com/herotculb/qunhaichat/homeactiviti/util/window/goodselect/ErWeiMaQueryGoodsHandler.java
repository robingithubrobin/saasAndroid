package com.herotculb.qunhaichat.homeactiviti.util.window.goodselect;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.herotculb.qunhaichat.R;
import com.herotculb.qunhaichat.dto.GoodsTableDto;
import com.herotculb.qunhaichat.widget.LoadingDialog;

public class ErWeiMaQueryGoodsHandler extends Handler{
	Activity context;
	LoadingDialog dialog;
	Class returnclass;
	String type;
	public ErWeiMaQueryGoodsHandler(Activity context2,
			LoadingDialog dialog,Class returnclass,String type) {
		super();
		this.context = context2;
		this.dialog = dialog;
		this.returnclass=returnclass;
		this.type=type;
	}
	@SuppressLint("ResourceAsColor")
	@Override
	public void handleMessage(android.os.Message msg) {
		final Map<String, Object> map = (Map<String, Object>) msg.obj;
		Boolean b=(Boolean) map.get("success");
		dialog.hide();
		dialog.dismiss();
		BootstrapEditText nowpage=(BootstrapEditText) context.findViewById(R.id.query_goods_nowpage);
		if(b){
			 List<GoodsTableDto> list=(List<GoodsTableDto>) map.get("data");
			 GoodsTableDto dto=list.iterator().next();
			 Intent i = new Intent(context,returnclass);
				Bundle bun = new Bundle();
				bun.putString("type", "goodsSelect");
				bun.putString("type2",type);
				bun.putString("name", dto.getGoodsName());
				bun.putString("num", dto.getGoodsNum()+"");
				bun.putString("price", dto.getPrice()+"");
				bun.putString("model", dto.getGoodsModel());
				bun.putString("types", dto.getGoodsType());
				bun.putString("tiaoma", dto.getCodeid());
				bun.putString("pinyin", dto.getSpell());
				bun.putString("inprice", dto.getInPrice()+"");
				bun.putString("id", dto.getId()+"");
				bun.putString("salesnum", dto.getSalesNum()+"");
				bun.putString("score", dto.getScore()+"");
				bun.putString("totalinprice", dto.getTotalInPrice()+"");
				bun.putString("totalprice", dto.getTotalPrice()+"");
				i.putExtras(bun);
				context.setResult(context.RESULT_OK, i);
				context.finish();

		}else{
			String codeid=(String)map.get("codeid");
			Intent i = new Intent(context,returnclass);
			Bundle bun = new Bundle();
			bun.putString("type2",type);
			bun.putString("type", "goodsSelectnull");
			bun.putString("codeid", codeid);
			i.putExtras(bun);
			context.setResult(context.RESULT_OK, i);
			context.finish();
			
		}
	}
}
