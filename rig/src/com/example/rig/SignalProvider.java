package com.example.rig;

 import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignalProvider extends Service implements  GestureRecorderListener  {
	GestureRecorder recorder;
	private IMultiplier multiplierService;
	private boolean isAidlBound;

	public float accel;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
        recorder.registerListener(this);

		return SignalProviderStub;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		recorder.unregisterListener(this);
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		recorder = new GestureRecorder(this);
 		super.onCreate();
	}
	
	
	IBinder SignalProviderStub = new ISignalProvider.Stub(){

		@Override
		public void start() throws RemoteException {
			
			// TODO Auto-generated method stub
			//do stuff
			
			
					
					// recorder = new GestureRecorder();
				        Log.d("in START", "bIndAidl");
				        bindAidlService();
						Toast.makeText(getApplicationContext(), "BOUND FUCK!", Toast.LENGTH_SHORT).show();

				}
		
		
		
		
	};

	 private void doMultiply(){
	    	if(isAidlBound){
	    		
	    		try {
					multiplierService.multiply(accel);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}
	    }
	    
	public void bindAidlService(){
		
		Intent intent = new Intent();
		intent.setClassName(this.getPackageName(),"com.example.rig.ClientAidl");
		boolean b = this.bindService(intent, myAidlConnection, BIND_AUTO_CREATE);
		Log.d("GO", "bound? "+b);
		   recorder.start();
		   Log.d("Start", "Accel  ");
	}


	public void onDestroy(){
		
		
		if(isAidlBound)
			this.unbindService(myAidlConnection);
		super.onDestroy();
		
	}


	   
	    
	    private ServiceConnection myAidlConnection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				multiplierService=null;
				isAidlBound=false;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				multiplierService = IMultiplier.Stub.asInterface(service);
			isAidlBound=true;
			Toast.makeText(getApplicationContext(), "OnServiceConn", Toast.LENGTH_SHORT).show();

			}
		};
		@Override
		public void onGestureRecorded(float value) {
			// TODO Auto-generated method stub
			accel=value;
	    	//txt.setText("Value: " + Float.toString(value));
	    	Log.d("Main Activiey", "Value: " + Float.toString(value));
	    	doMultiply();
			
		}

	
	
}

	