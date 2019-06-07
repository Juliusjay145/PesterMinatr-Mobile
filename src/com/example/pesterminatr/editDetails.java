package com.example.pesterminatr;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class editDetails extends Activity {

	EditText eproblem,eaddress,edate,etime;
	Button btnSave;
	SharedPreferences prf;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdetails);
		
		eproblem = (EditText) findViewById(R.id.editText1);
		eaddress = (EditText) findViewById(R.id.editText2);
		edate = (EditText) findViewById(R.id.editText3);
		etime = (EditText) findViewById(R.id.editText4);
		
		btnSave = (Button) findViewById(R.id.button1);
	}
	
	

}
