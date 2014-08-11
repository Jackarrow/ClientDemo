package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageLoader {
	private ImageView image;
	public ImageLoader(ImageView image){
		this.image = image;
	}
	public void loadImage(){
		new Thread(){
			public void run() {
			try {
				URL url = new URL((String) image.getTag());
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();
				Bitmap bit = BitmapFactory.decodeStream(is);
				Message msg = handler.obtainMessage();
				msg.obj = bit;
				handler.sendMessage(msg);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			}
		}.start();
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
				image.setImageBitmap((Bitmap) msg.obj);
		}
	};
	
}
