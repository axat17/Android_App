package akshat.com.kiddie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHomeActivity extends AppCompatActivity {


    Button btnEdit=null;
    Button btnReset=null;
Button btnSeniorlist = null;
    Button btnLogout=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        String id=sf.getString("cid","0");

        btnEdit= (Button)findViewById(R.id.cmdEditProfileD);
        btnReset=(Button)findViewById(R.id.cmdChangePassD);
btnSeniorlist=(Button)findViewById(R.id.btnSeniorListDr);

        btnSeniorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intt= new Intent(UserHomeActivity.this,SeniorListDriverActivity.class);
                startActivity(intt);
            }
        });

        btnLogout =(Button)findViewById(R.id.cmdLogoutD);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent intt=  new Intent(UserHomeActivity.this,StartActivity.class);
                startActivity(intt);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt= new Intent(UserHomeActivity.this,EditProfileDActivity.class);
                startActivity(intt);
            }

        });





        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt= new Intent(UserHomeActivity.this,ChangePass.class);
                startActivity(intt);

            }
        });



    }

}
