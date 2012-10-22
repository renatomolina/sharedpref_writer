package br.com.fiatwriter;

import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;

public class WriterActivity extends Activity {

	private final String PREF_COL  = "PREF_FIAT";
	private final String PREF_VAR  = "PREF_VAR";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer);
        handler.sendEmptyMessage(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_writer, menu);
        return true;
    }
    
    private void write(){
    	
    	SharedPreferences pref = this.getSharedPreferences(PREF_COL, Context.MODE_WORLD_READABLE);
		Editor ed =  pref.edit();
		ed.putString(PREF_VAR, (new Date()).toString());
		ed.commit();
    }
    
    private Handler handler = new Handler(){
    	
    	@Override
    	public void handleMessage(Message m){
    		
    		handler.postDelayed(new Runnable() {
				
				public void run() {
					write();
					handler.sendEmptyMessage(0);
				}
			}, 2000);
    	}
    };

    
}
