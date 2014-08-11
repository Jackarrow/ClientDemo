package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.os.Handler;
import android.os.Message;

public class MyThread extends Thread {
	private String urlString;
	private Handler handler;
	public MyThread(String url,Handler handler) {
		this.handler = handler;
		this.urlString = url;
	}
	@Override
	public void run() {
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			ParseXmlStream pxs = new ParseXmlStream(is);
			List<AppBean> beans = pxs.parser();
			System.out.println("app列表的大小"+beans.size());
			Message msg = handler.obtainMessage();
			msg.what = 1;
			msg.obj = beans;
			handler.sendMessage(msg);
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
