/*
split back into register and write
of register fail callback then register again
cahnge js file so there are commands register, black, red, green
export as LED

*/

package com.digistump.digicdc;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.BroadcastReceiver;
import android.hardware.usb.UsbManager;
import java.io.IOException;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.KeyEvent;
import android.util.Log;
import android.content.Context;
import android.app.PendingIntent;
import android.provider.Settings;
import  android.app.Activity;

import jp.ksksue.driver.serial.FTDriver;




public class Digicdc extends CordovaPlugin {
  private static final String ACTION_USB_PERMISSION ="com.digistump.digicdc.USB_PERMISSION";
  private static final String ACTION_PERM  ="register";
  private static final String ACTION_WRITE ="write";


    FTDriver mSerial;

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {


        try {

          if(ACTION_PERM.equals(action)){




            String permission = "android.permission.USB_PERMISSION";
            int res = cordova.getActivity().checkCallingOrSelfPermission(permission);
            if (res != PackageManager.PERMISSION_GRANTED){

                    mSerial = new FTDriver((UsbManager) cordova.getActivity().getSystemService(Context.USB_SERVICE));
                    PendingIntent permissionIntent = PendingIntent.getBroadcast(cordova.getActivity(), 0, new Intent(
                          ACTION_USB_PERMISSION), 0);
                    mSerial.setPermissionIntent(permissionIntent);
                    if (!mSerial.begin(FTDriver.BAUD9600)) {
                      callbackContext.error("Fail");
                      return false;
                    }
                    else{
                      callbackContext.success("GOT PERM");
                      return true;
                    }
           }
           callbackContext.success("HAVE PERM");
            return true;
          }
          else{
          


            JSONObject arg_object = args.getJSONObject(0);
            final String strWrite = arg_object.getString("text");
            

            

            
            cordova.getThreadPool().execute(new Runnable() {
              public void run() {

                  if (!mSerial.begin(FTDriver.BAUD9600)) {
                    callbackContext.error("Fail");
               

                  }
                  
                  mSerial.write(strWrite.getBytes(), strWrite.length());

                  mSerial.end();
                  callbackContext.success("OK");

              }
            });
                 
            
            return true;
          }
  

          
        


            
        } catch(Exception e) {
            System.err.println("Error");
            callbackContext.error("FAIL2");
            return false;
        } 
    }
}
