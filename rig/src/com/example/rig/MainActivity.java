package com.example.rig; 



 
 import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
   
  ISignalProvider SignalProvider;
	double minDistance = Double.MAX_VALUE;
Button btnTrain,btnTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnTrain=(Button)this.findViewById(R.id.btnTrain);
		btnTest=(Button)this.findViewById(R.id.btnTest);
		btnTrain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					SignalProvider.setCommit(true);
					Toast.makeText(getApplicationContext(), "trainButtone work", Toast.LENGTH_SHORT).show();

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
btnTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					SignalProvider.setTest(true);
			
					Toast.makeText(getApplicationContext(), "testButtone work", Toast.LENGTH_SHORT).show();

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
  
	private final ServiceConnection SignalConnection= new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder service) {
			// TODO Auto-generated method stub
			SignalProvider= ISignalProvider.Stub.asInterface(service);
			
				Toast.makeText(getApplicationContext(), "OnServiceConn", Toast.LENGTH_SHORT).show();

				try {
					Log.d("MainAct", "onSerConn");
					SignalProvider.start();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

		
			
 
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			SignalProvider=null;
		}};
	
	
	
	@Override
	protected void onResume() {
		
		Toast.makeText(getApplicationContext(), "intent", Toast.LENGTH_SHORT).show();

	Intent BindIntent= new Intent("com.example.rig.SignalProvider");
	bindService(BindIntent, SignalConnection,Context.BIND_AUTO_CREATE);
	super.onResume();

	}
	
	@Override
	protected void onPause() {
		
		
		SignalProvider = null;
		unbindService(SignalConnection);
		super.onPause();
	}

  
    
 



}