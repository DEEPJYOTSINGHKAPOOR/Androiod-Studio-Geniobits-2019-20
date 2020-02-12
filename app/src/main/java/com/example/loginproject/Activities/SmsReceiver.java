package com.example.loginproject.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


//this code runs when someone sends otp
public class SmsReceiver extends BroadcastReceiver
{
    private final String TAG="IncomingSms" ;
    private final String SMS_BUNDLE="pdus" ;
    private static SmsListener smsListener ;
    Boolean b ;
    String abcd,xyz ;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        final Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            final Object[] pdus = (Object[]) bundle.get(SMS_BUNDLE);
            msgs = new SmsMessage[pdus.length];

            if(pdus !=null){
                for (int i=0; i<msgs.length; i++)
                {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[])pdus[i]);

                    String sender =smsMessage.getDisplayOriginatingAddress() ;
                    b=sender.endsWith("Abhishek");

                    String messageBody =smsMessage.getMessageBody() ;
                    abcd = messageBody.replaceAll("[^0-9]","");

                    if(b==true){
                        smsListener.messageReceived(abcd);
                    }
                    else
                    {

                    }
//
//
//                    str += "SMS from " + msgs[i].getOriginatingAddress();
//                    str += " :";
//                    str += msgs[i].getMessageBody().toString().split(":")[1];
//                    str += "\n";
//
//                    Intent myIntent =new Intent("otp");
//                    myIntent.putExtra("message",str);
//                    LocalBroadcastManager.getInstance(context).sendBroadcast(myIntent);
                }
            }
            //---display the new SMS message---
//            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show();
    }

    private static void bindListner(SmsListener listener) {
        smsListener=listener ;
    }
}