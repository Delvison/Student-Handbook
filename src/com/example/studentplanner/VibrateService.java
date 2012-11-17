package com.example.studentplanner;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

public class VibrateService extends Service {
    private AudioManager amanager;  
    int prevVolume;
    
    public void onCreate(){
    	super.onCreate();
    }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//bind activity to a service
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		//this is what runs when we call startService();
		//get the systems audio manager
        amanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);  
        int prevVolume = amanager.getRingerMode();
        
        
		return START_STICKY; //keeps going until explicitly stopped
		
	}
	@Override
	public void onDestroy(){
		
	}
    //called when the user is in class
	public void inClass() {
		amanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	}
	//called when the user is out of class
	public void outClass() {
		amanager.setRingerMode(prevVolume);
		
	}

}

//startService(new Intent(getBaseContext(), VibrateService.class))
   //scheduling services using alarm manager
//http://justcallmebrian.com/?p=129