package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivityRegistrierung extends AppCompatActivity
{

    //EditTextVariablen
    private EditText passwort;
    private EditText email;
    private EditText nutzername;
    private EditText alter;
    private Button weiter;

    //Variablen
    private String passwortV;
    private String emailV;
    private String nutzernameV;
    private int alterV;
    private String alterS;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_registrierung);
        passwort = (EditText) findViewById(R.id.Passwort);
        email = (EditText) findViewById(R.id.Email);
        alter = (EditText) findViewById(R.id.Alter);
        nutzername= (EditText) findViewById(R.id.EditTextNutzername);
        weiter = (Button) findViewById(R.id.buttonWeiter);
        db=new DatabaseHandler(this);


    }

    public void weiter(View view)
    {
        boolean ausgelesen;


        ausgelesen=auslesen(view);

        if(ausgelesen==true) {
            String name=nutzernameV;
            String passwort=passwortV;
            int alter=alterV;
            String alterS="alter";
            String email=emailV;

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(nutzernameV, name);
            i.putExtra(passwortV, passwort);
            i.putExtra(emailV, email);
            i.putExtra(alterS,alter);

            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Bitte erneut versuchen!", Toast.LENGTH_SHORT).show();

        }
    }



    public boolean auslesen(View view) {
        boolean objektErstellt;
        if ((email.getText().length() != 0) && (passwort.getText().length() != 0) && (alter.getText().length() != 0) && (nutzername.getText().length() != 0)) {
            emailV = email.getText().toString();
            passwortV = passwort.getText().toString();
            alterS = alter.getText().toString();
            alterV = Integer.valueOf(alterS);
            nutzernameV = nutzername.getText().toString();

            return true;



        } else {
            Toast.makeText(this, "Bitte füllen Sie alle erforderlichen Felder!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean objektErstellen(String name, int alter, String email, String passwort) {
        //Nutzer aus der Activity
        System.out.println("test");
        System.out.println(name);

        User user = new User(name, alter, email, passwort);
        //Nutzer aus der Datenbank
        User dbUser;
        //Erstellen der Datenbank
        //speichern des Namens aus der Activity
        String usernameU = user.getName(); //gewünschten Namen (User) einlesen
        //schauen ob der name schon drin ist
        dbUser = db.getUser(usernameU);

        if (dbUser != null && dbUser.getName().equals(user.getName())) {
            Toast.makeText(this, " Name bereits vergeben", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            db.addUser(user); //typ hinzufügen
            Toast.makeText(this, "Registrierung erfolgreich!", Toast.LENGTH_SHORT).show();
            return true;
        }


    }
 /*
        weiter.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(passwort.getText()!=null && nutzername.getText()!=null) {
                            auslesen(v);
                        }
                        else
                        {
                            // Intent intent = new Intent(MainActivity.this, Auswahl.class);
                            //  intent.putExtra("Nutzername:", nuna);
                            //  intent.putExtra("Passwort:", pw);
                        }
                    }
                }
        );
        */


}
