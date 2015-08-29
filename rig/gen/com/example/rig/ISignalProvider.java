/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\1Bs.Cs\\my fyp java work\\Prototype interface\\Rig\\rig\\src\\com\\example\\rig\\ISignalProvider.aidl
 */
package com.example.rig;
public interface ISignalProvider extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.rig.ISignalProvider
{
private static final java.lang.String DESCRIPTOR = "com.example.rig.ISignalProvider";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.rig.ISignalProvider interface,
 * generating a proxy if needed.
 */
public static com.example.rig.ISignalProvider asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.rig.ISignalProvider))) {
return ((com.example.rig.ISignalProvider)iin);
}
return new com.example.rig.ISignalProvider.Stub.Proxy(obj);
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
case TRANSACTION_start:
{
data.enforceInterface(DESCRIPTOR);
this.start();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.rig.ISignalProvider
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
@Override public void start() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_start, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_start = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void start() throws android.os.RemoteException;
}
