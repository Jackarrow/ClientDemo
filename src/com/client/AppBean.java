package com.client;

import android.os.Parcel;
import android.os.Parcelable;

public class AppBean implements Parcelable{
	private String name;
	private String packgeName;
	private String version;
	private String versionName;
	private String appIcon;
	private String appUrl;
	private String appSize;
	private String appDescription;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackgeName() {
		return packgeName;
	}
	public void setPackgeName(String packgeName) {
		this.packgeName = packgeName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getAppSize() {
		long data = Long.valueOf(appSize);
		float m = data/1024f/1024;
		String size = m+"";
		String[] aa = size.split("\\.");
		return aa[0]+"."+aa[1].substring(0, 2)+"M";
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	
	public static Parcelable.Creator<AppBean> Creator = new Creator<AppBean>() {

		@Override
		public AppBean createFromParcel(Parcel source) {
			AppBean bean = new AppBean();
			return null;
		}

		@Override
		public AppBean[] newArray(int size) {
			return null;
		}
		
	};
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
	}
	
}
