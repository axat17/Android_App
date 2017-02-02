package akshat.com.kiddie;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by akshat on 11/09/2016.
 */

public class SeniorDataAdapter extends BaseAdapter  {
    private Activity activity;
    private String[] nms;
    private String[] ages;
    private String[] gend;
    private String[] lic;
    private String[] pic;

    String mobileno="";
    private static LayoutInflater inflater = null;

    private View vi;

    public SeniorDataAdapter(Activity a, String[] nms, String[] ages, String gend[], String[] lic, String[] pic) {
        activity = a;
        this.nms = nms;
        this.ages = ages;
        this.gend = gend;
        this.lic = lic;
        this.pic = pic;

        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        SharedPreferences sf = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        mobileno = sf.getString("cid", "0");

        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache

        Log.d("KIDDIE","Constructor Called");
    }

    public int getCount() {
        return nms.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView txtSPersonName;
        public TextView txtSAge;
        public TextView txtSGender;
        public TextView txtSLic;
        public TextView txtSPicup;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        vi = convertView;
        final ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.row_senior, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.txtSPersonName = (TextView) vi.findViewById(R.id.txtRowSeniorName);
         //   holder.txtSAge=(TextView)vi.findViewById(R.id.txtRows);
            holder.txtSPicup=(TextView)vi.findViewById(R.id.txtRowSeniorPickupStand);
            holder.txtSGender=(TextView)vi.findViewById(R.id.txtRowSeniorGender);
           // holder.txtQDates=(TextView)vi.findViewById(R.id.txtQDate);

          /*  holder.btnViewAnswer=(Button) vi.findViewById(R.id.btnViewAnswer);

            holder.btnViewAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // String qid = holder.txtQID.getText().toString().split(":")[1];
                    // Intent intt= new Intent(activity,AnswerList.class);
                    // intt.putExtra("que",holder.txtQue.getText().toString());

                    // intt.putExtra("qid",qid);
                    // activity.startActivity(intt);
                }
            });
            //   holder.btnGiveAnswer=(Button) vi.findViewById(R.id.btnGiveAnswer);



       /*    holder.btnGiveAnswer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.give_answer);
                    dialog.setTitle("Title...");



                    dialog.show();

                }
            });
            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();


        holder.txtSPersonName.setText("Name  :" + nms[position]);
        holder.txtSGender.setText("Gender :"+gend[position]);
        holder.txtSPicup.setText("Pickup From :"+pic[position]);

       /* holder.txtQType.setText("Category     :" + qtype[position]);
        holder.txtQue.setText("Question :" + questions[position]);
        holder.txtQEnr.setText("Asked By:" + qenrollment[position]);
        holder.txtQDates.setText("On:   " + qdate[position]);
*/
        //  holder.btnGiveAnswer.setTag(questionid[position]);

        //DisplayImage function from ImageLoader Class
        //Uncomment this for Lazy
        // imageLoader.DisplayImage(images[position], image);

        /******** Set Item Click Listner for LayoutInflater for each row ***********/
        // vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }







}
