package com.example.practiceproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practiceproject.responsedialogfragment.ConfirmDialog;
import com.google.android.material.button.MaterialButton;

public class Submit extends AppCompatActivity implements View.OnClickListener {
   Toolbar toolbar;
   EditText firstName;
   EditText lastName;
   EditText email;
   EditText gitHubLink;
   MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        email=findViewById(R.id.emailAddress);
        gitHubLink=findViewById(R.id.gitHubLink);

        submit=findViewById(R.id.submit2);
        submit.setOnClickListener(this);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.submit2:
                String fName=firstName.getText().toString();
                String lName=lastName.getText().toString();
                String mail=email.getText().toString();
                String git=gitHubLink.getText().toString();
                if(fName.equals(" ")||lName.equals(" ")||mail.equals(" ")|| git.equals(" ")){
                    Toast.makeText(this,"Fill in the required field",Toast.LENGTH_LONG).show();
                }
                else{
                    ConfirmDialog dialog=new ConfirmDialog();
                    Bundle bundle=new Bundle();
                    bundle.putString(ConfirmDialog.FIRST_NAME,fName);
                    bundle.putString(ConfirmDialog.LAST_NAME,lName);
                    bundle.putString(ConfirmDialog.EMAIL,mail);
                    bundle.putString(ConfirmDialog.GIT,git);

                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(),"Confirm");
                }

        }
    }
}