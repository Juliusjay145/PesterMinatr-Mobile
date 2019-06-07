package com.example.pesterminatr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class service extends Activity implements OnClickListener {

	ImageButton imgb1,imgb2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pestcontrol_service);
		
		imgb1 = (ImageButton) findViewById(R.id.imageButton1);
		imgb2 = (ImageButton) findViewById(R.id.imageButton2);
		
		
		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
		
		imgb1.setOnClickListener(this);
		imgb2.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String ID = getIntent().getStringExtra("service");
		String PestID = getIntent().getStringExtra("pestId");
		if(arg0==imgb1){
			Intent intent = new Intent(this, residential.class);
			intent.putExtra("serviceID", ID);
			intent.putExtra("pestID", PestID);
			startActivityForResult(intent,1);
		}
		else{
			Intent intent = new Intent(this, commercial.class);
			intent.putExtra("serviceID", ID);
			intent.putExtra("pestID", PestID);
			startActivityForResult(intent,1);
		}
		 
	}
	
	
	
	
	
	

}
