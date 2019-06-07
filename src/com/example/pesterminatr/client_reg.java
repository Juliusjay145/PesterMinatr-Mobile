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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class client_reg extends Activity implements OnClickListener {
	
	EditText txtname,txtlastname,txtaddress,txtcontact,txtusername,txtpassword;
	Button btnRegister;
	InputStream is;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.client_register);
		txtname = (EditText) findViewById(R.id.txtcname);
		txtlastname = (EditText) findViewById(R.id.editText1);
		txtaddress = (EditText) findViewById(R.id.txttime);
		txtcontact = (EditText) findViewById(R.id.editText5);
		txtusername = (EditText) findViewById(R.id.txtdate);
		txtpassword = (EditText) findViewById(R.id.editText4);
		btnRegister = (Button) findViewById(R.id.btnEdit);
		btnRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		
		String client_name = txtname.getText().toString();
		String client_lastname = txtlastname.getText().toString();
		String client_address = txtaddress.getText().toString();
		String client_contact = txtcontact.getText().toString();
		String username = txtusername.getText().toString();
		String password = txtpassword.getText().toString();
		
		if(client_name.equals("") || client_address.equals("") || username.equals("")|| password.equals("") || client_contact.equals(""))
		{
			Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
		}
		else{
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("client_name", client_name));
			nameValuePairs.add(new BasicNameValuePair("client_lastname", client_lastname));
			nameValuePairs.add(new BasicNameValuePair("client_address", client_address));
			nameValuePairs.add(new BasicNameValuePair("client_contact", client_contact));
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));
			try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("http://10.0.2.2/mobile/create_client.php");
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				is=entity.getContent();
				Toast.makeText(getApplicationContext(), client_name+"Your Account has been register successfully", Toast.LENGTH_SHORT).show();
				txtname.setText("");
				txtaddress.setText("");
				txtcontact.setText("");
				txtusername.setText("");
				txtpassword.setText("");
				
				
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
	
	
}
