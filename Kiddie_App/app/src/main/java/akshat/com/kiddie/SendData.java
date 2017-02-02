package akshat.com.kiddie;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by akshat on 10/23/2016.
 */
public class SendData  extends AsyncTask<String, Void, String> {

    Context context;
    String callpage;
    String resultpage;
    String []param;

    public SendData(Context context,String callpage,String resultpage)
    {
        this.context= context;
        this.callpage=callpage;
        this.resultpage=resultpage;
    }
    @Override
protected String doInBackground(String... params) {
       this.param = params;
        String msg= postData(params);
        return msg;
        }

@Override
protected void onPostExecute(final String result) {
   // Toast.makeText(context,"Process Over",Toast.LENGTH_LONG).show();
    Common.msg=result;


    if(result.split(":").length>1)
    {
        if(callpage.equals("login.php"))
        {
            SharedPreferences sf= context.getSharedPreferences("User",Context.MODE_PRIVATE);
            SharedPreferences.Editor edit= sf.edit();

            edit.putString("cid",result.split(":")[1]);
            edit.commit();

            if(result.contains("Driver")) {

                Intent intt = new Intent(context, UserHomeActivity.class);
                context.startActivity(intt);
                ((Activity)context).finish();
            }
            else if(result.contains("Parent"))
            {
                Intent intt = new Intent(context, ParentHomeActivity.class);
                context.startActivity(intt);
                ((Activity)context).finish();
            }
        }
        else if(callpage.equals("parentregister.php") || callpage.equals("driverregister.php"))
        {
            Intent intt=new Intent(context,MainActivity.class);
            context.startActivity(intt);
            ((Activity)context).finish();
        }

    }
    else if(callpage.equals("senioradd.php"))
    {
        Intent intt=new Intent(context,SeniorDetailActivity.class);
        context.startActivity(intt);
    }
    else
    {
        AlertDialog.Builder alertdg= new AlertDialog.Builder(context);
        alertdg.setTitle(result);
        alertdg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertdg.show();

    }

        }

@Override
protected void onPreExecute() {}

@Override
protected void onProgressUpdate(Void... values) {}


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
            /*"cnm=" + URLEncoder.encode(cnm, charset);
            s += "&cad=" + URLEncoder.encode(cad, charset);
            s += "&cps=" + URLEncoder.encode(cps, charset);
            s += "&mob=" + URLEncoder.encode(mob, charset);
            s += "&city=" + URLEncoder.encode(city, charset);
*/
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
