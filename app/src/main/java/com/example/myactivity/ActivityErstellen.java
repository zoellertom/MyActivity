package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ActivityErstellen extends AppCompatActivity {




    private EditText activityName;
    private EditText beschreibung;
    private CheckBox aktuellePosition;
    private CheckBox positionWaehlen;
    private EditText xPos;
    private EditText yPos;
    private Button erstellen;



    public String activityNameV;
    private String beschreibungV;
    private boolean aktuellePositionV;
    private boolean positionWaehlenV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellen);
        DatabaseHandler db=new DatabaseHandler(this);

        activityName=(EditText) findViewById(R.id.ActivityName);
        beschreibung=(EditText) findViewById(R.id.Beschreibung);
        aktuellePosition=(CheckBox)findViewById((R.id.checkboxAktuellePosition));
        positionWaehlen=(CheckBox)findViewById(R.id.checkboxPositionWaehlen);
        xPos=(EditText)findViewById(R.id.xPosition);
        yPos=(EditText)findViewById((R.id.yPosition));
        erstellen=(Button)findViewById((R.id.buttonErstellen));



    }

    public void viewAuslesen(View v)
    {
        if(aktuellePosition.isChecked())
        {
            aktuellePositionV=true;
        }
        else
        {
            aktuellePositionV=false;
        }

        if(positionWaehlen.isChecked())
        {
            positionWaehlenV=true;
        }
        else
        {
            positionWaehlenV=false;
        }
        
        if(activityName.getText()!=null && aktuellePositionV==true)
        {
         activityNameV=activityName.getText().toString();


            //aktuelle Koordinaten einspeichern API
        }

    }


}
