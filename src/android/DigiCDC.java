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
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.CommandCapture;
import com.stericson.RootTools.execution.Command;



public class Digicdc extends CordovaPlugin {
  private static final String ACTION_USB_PERMISSION ="com.digistump.digicdc.USB_PERMISSION";
  private static final String ACTION_PERM  ="register";
  private static final String ACTION_WRITE ="write";
  private static final String ACTION_HIDE ="hide";
  private static final String ACTION_WIFI ="wifi";


    FTDriver mSerial;

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {


        try {

          if(ACTION_HIDE.equals(action)){
            CommandCapture command = new CommandCapture(0, "service call activity 42 s16 com.android.systemui");
            RootTools.getShell(true).add(command);

          }

          else if(ACTION_WIFI.equals(action)){

            CommandCapture command = new CommandCapture(0, "am startservice -n com.android.systemui/.SystemUIService");
            RootTools.getShell(true).add(command);

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
            cordova.getActivity().startActivity(intent);



          }
          
          else if(ACTION_PERM.equals(action)){




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
