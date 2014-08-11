package com.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class ParseXmlStream {
	private InputStream is;
	public ParseXmlStream(InputStream is){
		this.is = is;
	}
	public List<AppBean> parser(){
		List<AppBean> beans = new ArrayList<AppBean>();
		XmlPullParser parser = Xml.newPullParser();
		AppBean bean = null;
		try {
			parser.setInput(is,"utf-8");
			int event = parser.getEventType();
			while(event!=XmlPullParser.END_DOCUMENT){
				switch(event){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("App")){
						bean = new AppBean();
					}else if(parser.getName().equals("App-name")){
						event = parser.next();
						bean.setName(parser.getText());
					}else if(parser.getName().equals("App-pack")){
						event = parser.next();
						bean.setPackgeName(parser.getText());
					}else if(parser.getName().equals("Version-code")){
						event = parser.next();
						bean.setVersion(parser.getText());
					}else if(parser.getName().equals("Version-name")){
						event = parser.next();
						bean.setVersionName(parser.getText());
					}else if(parser.getName().equals("Version-icon")){
						event = parser.next();
						bean.setAppIcon(parser.getText());
					}else if(parser.getName().equals("Version-file")){
						event = parser.next();
						bean.setAppUrl(parser.getText());
					}else if(parser.getName().equals("Version-file-size")){
						event = parser.next();
						bean.setAppSize(parser.getText());
					}else if(parser.getName().equals("Version-detail")){
						event = parser.next();
						bean.setAppDescription(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					if(parser.getName().equals("App")){
						beans.add(bean);
						bean = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return beans;
	}
}
