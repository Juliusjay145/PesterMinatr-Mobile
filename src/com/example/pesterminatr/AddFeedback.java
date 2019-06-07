package com.example.pesterminatr;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class AddFeedback extends Activity implements OnClickListener {

	RatingBar rate;
	EditText txtComment;
	Button btnSubmit;
	SharedPreferences prf;
	InputStream is;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_feedback);
		
		prf = getSharedPreferences("user_details", MODE_PRIVATE);
		
		rate = (RatingBar) findViewById(R.id.ratingBar1);
		txtComment = (EditText) findViewById(R.id.editText1);
		btnSubmit = (Button) findViewById(R.id.button1);
		btnSubmit.setOnClickListener(this);
		String ID = getIntent().getStringExtra("pestcontrol");
		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View arg0) {
		
		//String rateStars = "Your rate" + rate.getNumStars();
		
		String rateNumber = String.valueOf(rate.getRating());
		String comment = txtComment.getText().toString();
		String ID = getIntent().getStringExtra("pestcontrol");
		String username = prf.getString("client_name", null);
		
		Toast.makeText(getApplicationContext(), rateNumber, Toast.LENGTH_SHORT).show();
		
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("rating", rateNumber));
		nameValuePairs.add(new BasicNameValuePair("comment", comment));
		nameValuePairs.add(new BasicNameValuePair("pestcontrol_id", ID));
		nameValuePairs.add(new BasicNameValuePair("client_id", username));
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://192.168.43.118/project/index.php/androidcontroller/add_feedback");
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			is=entity.getContent();
			Toast.makeText(getApplicationContext(), "Rated Successfully", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, client.class);
			startActivity(intent);
			
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
	
	

}
