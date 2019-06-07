package com.example.pesterminatr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSchedule extends Activity implements OnClickListener {

	EditText txtDate,txtTime;
	Button btnAdd;
	InputStream is;
	SharedPreferences prf;
	DateFormat format=DateFormat.getDateInstance();
	Calendar calendar=Calendar.getInstance();
	int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
	int currentMinute = calendar.get(Calendar.MINUTE);
	TimePickerDialog timePickerDialog;
	String amPm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_schedule);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		txtDate = (EditText) findViewById(R.id.editText1);
		txtTime = (EditText) findViewById(R.id.editText2);
		btnAdd = (Button) findViewById(R.id.button1);
		btnAdd.setOnClickListener(this);
		txtDate.setOnClickListener(this);
		txtTime.setOnClickListener(this);
		
		
	}
	
	DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			calendar.set(Calendar.YEAR, arg1);
			calendar.set(Calendar.MONTH, arg2);
			calendar.set(Calendar.DAY_OF_MONTH, arg3);
			txtDate.setText(format.format(calendar.getTime()));
		}	
	};
	
	
	


	@Override
	public void onClick(View arg0) {
		
		timePickerDialog = new TimePickerDialog(AddSchedule.this, new TimePickerDialog.OnTimeSetListener() {
	        @Override
	        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
	        	String amPm;
//	        	if (hourOfDay >= 12) {
//	        	        amPm = "PM";
//	        	    } else {
//	        	        amPm = "AM";
//	        	    }
	        	if (hourOfDay > 12) {
	        		hourOfDay -= 12;
	                amPm = "PM";
	              } else if (hourOfDay == 0) {
	            	  hourOfDay += 12;
	                amPm = "AM";
	              } else if (hourOfDay == 12){
	            	  amPm = "PM";
	              }else{
	            	  amPm = "AM";
	              }
	        	txtTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
	        }
		}, currentHour, currentMinute, false);
		
		switch(arg0.getId()){
		case R.id.button1:
			
			String p_date = txtDate.getText().toString();
			String p_time = txtTime.getText().toString();
			
			//String temp_month = calendar.getMinimum(System.currentTimeMillis() - 10000);
			
			prf = getSharedPreferences("user_details",MODE_PRIVATE);
			String username = prf.getString("pestcontrol_id", null);
			
			
			
			
			
			if(p_date.equals("") || p_time.equals(""))
			{
					Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
			}
			else
			{
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("date", p_date));
				nameValuePairs.add(new BasicNameValuePair("time", p_time));
				nameValuePairs.add(new BasicNameValuePair("pestcontrol_id", username));
				try{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/add_schedule");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					is=entity.getContent();
					Toast.makeText(getApplicationContext(), "Schedule Added Successfully", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(this, Scheduled.class);
					startActivity(intent);
		//			txtname.setText("");
		//			txtaddress.setText("");
		//			txtcontact.setText("");
		//			txtusername.setText("");
		//			txtpassword.setText("");
					
					
				}
				catch(ClientProtocolException e)
				{
					Log.e("ClientProtocol","Log_tag");
					e.printStackTrace();
				}
				catch(IOException e)
				{
					Log.e("Log_tag", "IOException");
					e.printStackTrace();
				}
			}
				break;
				case R.id.editText1:
					new DatePickerDialog(this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
				break;
				
				case R.id.editText2:
					timePickerDialog.show();
				break;	
			
			
		
		
		}
		
		
		
		
		
	}
	
	

}
