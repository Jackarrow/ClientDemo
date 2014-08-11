package com.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroad extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals("str_url")){
			System.out.println("接收到广播");
		}
	}

}
