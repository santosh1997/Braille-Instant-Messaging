package com.example.keyboard;


import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	   public TextToSpeech t1;
	   public EditText ed1;
	   public EditText ed2;
	   public Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 ed1=(EditText)findViewById(R.id.enterValue);
		 ed2=(EditText)findViewById(R.id.messagecontent);
	      b1=(Button)findViewById(R.id.speak);
	      
	      t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
	         @Override
	         public void onInit(int status) {
	            if(status != TextToSpeech.ERROR) {
	               t1.setLanguage(Locale.UK);
	            }
	         }
	      });
	      b1.setOnClickListener(new View.OnClickListener() {
	          @Override
	          public void onClick(View v) {
	        	  
	             String toSpeak = "The number is "+numtoCharNum(ed1.getText().toString())+". And the sms text is "+ed2.getText().toString();
	             //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
	             t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
	          }
	       });
	      
	}
	 public void onPause(){
	      if(t1 !=null){
	         t1.stop();
	         t1.shutdown();
	      }
	      super.onPause();
	   }
	   
	 public void sendSMS(View view)
	 {
		 
		 //startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts(ed2.getText().toString(), ed1.getText().toString(), null)));
		 	SmsManager.getDefault().sendTextMessage(ed1.getText().toString(), null, ed2.getText().toString(), null, null);
	        Toast.makeText(getApplicationContext(), "Message Sent successfully",Toast.LENGTH_SHORT).show();
	        ed1.setText("");
	        ed2.setText("");
	        
	        
	 }
	 public String numtoCharNum(String number)
	 {
		 
		 String numReturn = "";
		 for(int i=0;i<number.length();i++)
		 {
			 char c = number.charAt(i);
			 switch(c)
			 {
			 case '+':
				 numReturn = numReturn + " plus"; 
				 break;
			 case '0':
				 numReturn = numReturn + " zero";
				 break;
			 case '1':
				 numReturn = numReturn + " one";
				 break;
			 case '2':
				 numReturn = numReturn + " two";
				 break;
			 case '3':
				 numReturn = numReturn + " three";
				 break;
			 case '4':
				 numReturn = numReturn + " four";
				 break;
			 case '5':
				 numReturn = numReturn + " five";
				 break;
			 case '6':
				 numReturn = numReturn + " six";
				 break;
			 case '7':
				 numReturn = numReturn + " seven";
				 break;
			 case '8':
				 numReturn = numReturn + " eight";
				 break;
			 case '9':
				 numReturn = numReturn + " nine";
				 break;
			 }
		 }
		 
		 
		 return numReturn;
	 }
}
