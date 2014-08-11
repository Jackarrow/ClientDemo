package com.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

import com.server.DownCallBack;
import com.server.SupportFunction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity{
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				List<AppBean> beans = (List<AppBean>) msg.obj;
				mList.setAdapter(new MyAdapter(beans, MainActivity.this));
				break;
			case 2:
				String data = msg.getData().getString("data");
				String key = msg.getData().getString("key");
				TextView text = (TextView) msg.obj;
				text.setText(data+"%");
				if(data.equals("100")){
					try {
						sf.unregCallback(callbacks.get(urls.indexOf(key)));
						indexor.put(urls.indexOf(key), null);
						bars.put(urls.indexOf(key), null);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				break;
			}

		}
	};
	private SparseArray<TextView> indexor = new SparseArray<TextView>();
	private List<String> urls = new ArrayList<String>();
	private SparseArray<ProgressBar> bars = new SparseArray<ProgressBar>();
	private SparseArray<DownCallBack> callbacks = new SparseArray<DownCallBack>();
	private SupportFunction sf = null;
	private ServiceConnection sc = new ServiceConnection() {
		

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			sf = SupportFunction.Stub.asInterface(service);
			try {
				System.out.println("得到的url"+sf.getAppListUrl());
				getAppList();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent service = new Intent(SupportFunction.class.getName());
		boolean isStart = bindService(service, sc, Context.BIND_AUTO_CREATE);
		mList = (ListView) findViewById(R.id.my_app_list);
		
		mList.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//当前有下载任务，再次点击加入下载队列，然后让下载队列开始下载
				String url = (String) parent.getItemAtPosition(position);
				if(urls.contains(url)){
					return;
				}
				urls.add(url);
				ProgressBar bar = (ProgressBar) view.findViewById(R.id.down_progress);
				bars.append(urls.indexOf(url), bar);
				TextView text = (TextView) view.findViewById(R.id.my_index);
				text.setText("等待下载中...");
				indexor.put(urls.indexOf(url), text);
		
				DownCallBack call = new DownCallBack.Stub() {
					
					@Override
					public void progress(String key, float porg) throws RemoteException {
						// TODO Auto-generated method stub
						System.out.println("下载的进度"+key+"--"+(porg*100)+"%");
						bars.get(urls.indexOf(key)).setProgress((int)(porg*100));
						Message msg = handler.obtainMessage();
						msg.obj = indexor.get(urls.indexOf(key));
						msg.what = 2;
						msg.getData().putString("data", (int)(porg*100)+"");
						msg.getData().putString("key", key);
						handler.sendMessage(msg);
						
					}
				};
				callbacks.put(urls.indexOf(url), call);
				if(sf!=null){
					try {
						sf.regCallback(call);
						sf.startDown(url);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void getAppList(){
		try {
			MyThread mt = new MyThread(sf.getAppListUrl(),handler);
			mt.start();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	DownCallBack mCallback = new DownCallBack.Stub() {
		
		@Override
		public void progress(String key, float porg) throws RemoteException {
			// TODO Auto-generated method stub
			System.out.println("下载的进度"+key+"--"+(porg*100)+"%");
//			if(porg==1){
//				sf.unregCallback(this);
//			}
		}
	};
	private ListView mList;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
//	@Override
//	public void onClick(View v) {
//
//		switch(v.getId()){
//		case R.id.button1:
//			try {
//				
//				mt.start();
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			break;
//		case R.id.button2:
//			try {
//				System.out.println(i+"-----");
//				sf.regCallback(mCallback);
//				if(i==0){
//					sf.startDown(url,mCallback);
//				}else{
//					sf.startDown(url2,mCallback);
//				}
//				i++;
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			break;
//		case R.id.button3:
//			break;
//		}
//	}
}
