package com.example.rig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


public class GestureClassifier {
	protected List<Gesture> trainingSet= Collections.emptyList();
	protected String activeTrainingSet = "walk";
	private final Context context;

	public GestureClassifier(Context context) {
		trainingSet = new ArrayList<Gesture>();   
		
		// initilization is imp
 		
		this.context = context;
	}
	
	
	public boolean commitData(Gesture g) {
		trainingSet.add(g);
		Toast.makeText( context, "comit", Toast.LENGTH_SHORT).show();

 			try {
				
				
				File sdcard = Environment.getExternalStorageDirectory();
				// to this path add a new directory path
				File dir = new File(sdcard.getAbsolutePath() + "/sdcard/MudaFit/");
				// create this directory if not already created
				dir.mkdir();
				// create the file in which we will write the contents
 				
				FileOutputStream fos = new FileOutputStream(new File("/sdcard/MudaFit/"+activeTrainingSet + ".gst").toString());
				ObjectOutputStream o = new ObjectOutputStream(fos);
				o.writeObject(trainingSet);
				Toast.makeText( context, "File Wrote", Toast.LENGTH_SHORT).show();

				o.close();
				fos.close();
				return true;
			} catch (IOException e) {
				Toast.makeText( context, "File fkd", Toast.LENGTH_SHORT).show();

				e.printStackTrace();
				return false;
			}
		
		
	}

	
}
