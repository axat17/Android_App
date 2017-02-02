package akshat.com.kiddie;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;

/**
 * Created by akshat on 10/08/2016.
 */

public class SeniorListDriverActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
String locname="Not Decided";
    ListView lstList=null;
    String []ids=null;
    String []nms={"First","jOHN"};
    String []ages={"33","66"};
    String []gen={"Male","mALE"};
    String []lic={"Lic-1","lic"};
    String []pic={"Ch02","P1"};
    String []mob=null;
    AppLocationService appLocationService=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_list_driver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        appLocationService = new AppLocationService(SeniorListDriverActivity.this);



        lstList= (ListView)findViewById(R.id.lstDriverSeniorList);


        lstList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final   String sname=nms[position];
                final String mobl = mob[position];
                new AlertDialog.Builder(SeniorListDriverActivity.this)
                        .setTitle("Notification")
                        .setMessage("Send Notification about Pickup?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

String msg= sname +" has been Picked Up ";

                                if (ContextCompat.checkSelfPermission(SeniorListDriverActivity.this,
                                        Manifest.permission.SEND_SMS)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    if (ActivityCompat.shouldShowRequestPermissionRationale(SeniorListDriverActivity.this, Manifest.permission.SEND_SMS)) {
                                    } else {
                                        ActivityCompat.requestPermissions(SeniorListDriverActivity.this, new String[]{Manifest.permission.SEND_SMS},
                                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                                    }
                                }

                                Location location = appLocationService
                                        .getLocation(LocationManager.GPS_PROVIDER);

                                if(location==null)
                                    location= appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
                                //you can hard-code the lat & long if you have issues with getting it
                                //remove the below if-condition and use the following couple of lines
                                //double latitude = 37.422005;
                                //double longitude = -122.084095

                                if (location != null) {
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();

                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    try {
                                        List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
                                        if(null!=listAddresses&&listAddresses.size()>0){
                                            String _Location = listAddresses.get(0).getAddressLine(0);
                                        locname=_Location;
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                msg+= " From "+ locname +" Lat "+latitude+","+longitude;
                                } else {
                                    showSettingsAlert();
                                }

                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                                String currentDateandTime = sdf.format(new Date());

                                msg+= " on "+ currentDateandTime;
                                SmsManager smsManager = SmsManager.getDefault();
Log.d("SENIOR",msg);

                                Toast.makeText(SeniorListDriverActivity.this, msg, Toast.LENGTH_SHORT).show();
                                //Uncomment below Line to Send SMS
                     //           smsManager.sendTextMessage(mobl, null, msg, null, null);



                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();



            }
        });
        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        String mob =sf.getString("mob","0");

        ReadJSONList R= new ReadJSONList();
        R.execute("getseniorlist.php","dmob",mob);

    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                SeniorListDriverActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        SeniorListDriverActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends android.os.Handler {

        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            locname=locationAddress;
           // tvAddress.setText(locationAddress);
        }

}
    class ReadJSONList extends AsyncTask<String, Void, String>
    {
        String []param;
        @Override
        protected String doInBackground(String... params) {
            this.param = params;
            String msg= postData(params);
            return msg;
        }

        @Override
        protected void onPostExecute(final String result) {

            try {
                JSONArray array = new JSONArray(result);

                if(array.length()==0)
                {
                    Toast.makeText(SeniorListDriverActivity.this,"No Seniors to Pickup for You",Toast.LENGTH_LONG).show();
                    return;
                }
                nms= new String [array.length()];
                ages=new String [array.length()];
                gen=new String [array.length()];
                lic=new String [array.length()];
                ids = new String[array.length()];
                pic= new String[array.length()];
                mob = new String[array.length()];

                for(int i=0;i<array.length();i++)
                {
                    JSONObject object= array.getJSONObject(i);
                    ids[i] = object.getString("intSeniorID");
                    nms[i]= object.getString("strSeniorName");
                    ages[i]=object.getString("intAge");
                    gen[i] = object.getString("strGender");
                    lic[i]=object.getString("strLicenceNo");
                    pic[i]= object.getString("strPickupStand");
                    mob[i]= object.getString("strMobileNo");
                }
                SeniorDataAdapter adp= new SeniorDataAdapter(SeniorListDriverActivity.this,nms,ages,gen,lic,pic);
                lstList.setAdapter(adp);
                lstList.invalidate();

            }catch (Exception E)
            {
                E.printStackTrace();
            }

        }

        public String postData(String... param) {//,String cnm, String cad, String cps, String mob, String city) {
            // Create a new HttpClient and Post Header
            String page=param[0];
            String msg="";
            try {

                java.net.URL url = new java.net.URL(Common.ip+page);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();



                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(45000);

                conn.setDoInput(true);
                conn.setDoOutput(true);

                String charset = "UTF-8";
                String s = "";

                for(int i=1;i<param.length;i++)
                {
                    s+= "&"+param[i++] + "=" + URLEncoder.encode(param[i], charset);
                }
                Log.d("SendData","SendData "+s);

                conn.setFixedLengthStreamingMode(s.getBytes().length);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(s);
                out.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    Log.d("SendData", "SendData:" + inputLine);
                    msg+=inputLine;
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed :"+e.getMessage();
//            Toast.makeText(context,"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
            return msg;
        }

    }
}
