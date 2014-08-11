/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\work\\ClientDemo\\src\\com\\server\\SupportFunction.aidl
 */
package com.server;
public interface SupportFunction extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.server.SupportFunction
{
private static final java.lang.String DESCRIPTOR = "com.server.SupportFunction";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.server.SupportFunction interface,
 * generating a proxy if needed.
 */
public static com.server.SupportFunction asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.server.SupportFunction))) {
return ((com.server.SupportFunction)iin);
}
return new com.server.SupportFunction.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getAppListUrl:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getAppListUrl();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_regCallback:
{
data.enforceInterface(DESCRIPTOR);
com.server.DownCallBack _arg0;
_arg0 = com.server.DownCallBack.Stub.asInterface(data.readStrongBinder());
this.regCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregCallback:
{
data.enforceInterface(DESCRIPTOR);
com.server.DownCallBack _arg0;
_arg0 = com.server.DownCallBack.Stub.asInterface(data.readStrongBinder());
this.unregCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startDown:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.startDown(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.server.SupportFunction
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getAppListUrl() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAppListUrl, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void regCallback(com.server.DownCallBack callBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_regCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregCallback(com.server.DownCallBack callBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startDown(java.lang.String url) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(url);
mRemote.transact(Stub.TRANSACTION_startDown, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getAppListUrl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_regCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unregCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_startDown = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public java.lang.String getAppListUrl() throws android.os.RemoteException;
public void regCallback(com.server.DownCallBack callBack) throws android.os.RemoteException;
public void unregCallback(com.server.DownCallBack callBack) throws android.os.RemoteException;
public void startDown(java.lang.String url) throws android.os.RemoteException;
}
