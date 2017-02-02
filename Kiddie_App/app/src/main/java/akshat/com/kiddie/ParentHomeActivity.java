package akshat.com.kiddie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by akshat on 09/22/2016.
 */

public class ParentHomeActivity extends AppCompatActivity {

    Button btnEdit=null;
    Button btnReset=null;

    Button btnAddSenior =null;
    Button btnLogout=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);



        SharedPreferences sf= getSharedPreferences("User", Context.MODE_PRIVATE);
        String id=sf.getString("cid","0");

        btnEdit= (Button)findViewById(R.id.cmdEditProfP);
        btnReset=(Button)findViewById(R.id.cmdChangePasssP);
        btnAddSenior = (Button)findViewById(R.id.cmdAddSenior);
        btnLogout =(Button)findViewById(R.id.cmdLogoutP);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent intt=  new Intent(ParentHomeActivity.this,StartActivity.class);
                startActivity(intt);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt= new Intent(ParentHomeActivity.this,EditProfilePActivity.class);
                startActivity(intt);
            }

        });


btnAddSenior.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intt= new Intent(ParentHomeActivity.this,SeniorDetailActivity.class);
        startActivity(intt);
    }
});


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt= new Intent(ParentHomeActivity.this,ChangePass.class);
                startActivity(intt);

            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
