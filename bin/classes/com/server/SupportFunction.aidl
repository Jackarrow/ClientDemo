package com.server;
import com.server.DownCallBack;

interface SupportFunction {
	String getAppListUrl();
	void regCallback(in DownCallBack callBack);
	void unregCallback(in DownCallBack callBack);
	void startDown(String url);
}
