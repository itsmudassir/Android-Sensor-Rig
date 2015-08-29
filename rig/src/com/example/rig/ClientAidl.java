package com.example.rig;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.Toast;

public class ClientAidl extends Service {

	
	 
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	private final IMultiplier.Stub binder = new IMultiplier.Stub() {
		
		@Override
		public void multiply(float a) throws RemoteException {
			final float result = a;
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable(){

				@Override
				public void run() {
					Toast.makeText(getApplicationContext(), "THREAD RAN"+Float.toString(result), Toast.LENGTH_SHORT).show();
					
				}
				
			});
			
		}
	};

}
