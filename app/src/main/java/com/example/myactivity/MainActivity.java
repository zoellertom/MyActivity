package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //EditTextVariablen
    private EditText passwort;
    //private EditText email;
    private EditText nutzername;
    // private EditText alter;
    private Button anmelden;
    private Button registrieren;
    //hhhh
    //Variablen
    private String passwortV;
    // private String emailV;
    private String nutzernameV;
    // private int alterV;
    // private String alterS;
    public DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwort = (EditText) findViewById(R.id.Passwort);
        //   email = (EditText) findViewById(R.id.EditTextEmail);
        //   alter = (EditText) findViewById(R.id.EditTextAlter);
        nutzername = (EditText) findViewById(R.id.Nutzername);
        registrieren = (Button) findViewById(R.id.buttonRegistrieren);
        anmelden = (Button) findViewById(R.id.buttonAnmelden);
        //Datenbank erstellen
        db = new DatabaseHandler(this);


    }

    public void registrieren(View view) {
        Intent i = new Intent(this, MyActivityRegistrierung.class);
        startActivity(i);
    }

    public void weiter(View view) {
        boolean auswerten;

        auswerten = auslesen(view);

        if (auswerten == true) {
            //DbAbfrage sonst kein Intent
            Intent i = new Intent(this, ActivityErstellen.class);
            Toast.makeText(this, "Login erfolgreich", Toast.LENGTH_SHORT).show();

            startActivity(i);
        } else {
            Toast.makeText(this, "Login nicht erfolgreich", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean auslesen(View view) {

        boolean dbAbfrageB;

        if ((passwort.getText().length() != 0) && (nutzername.getText().length() != 0)) {

            passwortV = passwort.getText().toString();
            passwortV.trim();
            nutzernameV = nutzername.getText().toString();
            nutzernameV.trim();
            //Abfrage der Datenbank mit Rückgabewert
            dbAbfrageB = dbAbfrage(db, nutzernameV, passwortV);


            if (dbAbfrageB == true) {
                return true;
            } else {
                return false;
            }
        } else {
            Toast.makeText(this, "Bitte füllen Sie alle erforderlichen Felder!", Toast.LENGTH_SHORT).show();
            return false;

        }
    }

//Problem in der DBAbfrage!!!!
    public boolean dbAbfrage(DatabaseHandler db, String uiNutzername, String uiPasswort) {
        User user;
        user = db.getUser(uiNutzername);
        //testausgabe
        if (user != null) {
            System.out.println("Test");
            System.out.println(user.getName());
            System.out.println(user.getPasswort());

            if (uiNutzername.equals(user.getName())) //wenn der Nutzer existiert
            {
                if (uiPasswort.equals(user.getPasswort()))  //wenn das Passwort übereinstimmt
                {
                    return true;

                } else //Wenn das Passwort nicht übereinstimmt
                {
                    Toast.makeText(this, "Falsches Passwort!", Toast.LENGTH_SHORT).show();
                    return false;

                }
            } else//wenn der Nutzer nicht existiert
            {
                Toast.makeText(this, "Nutzer wurde nicht gefunden!", Toast.LENGTH_SHORT).show();
                System.out.println("Test2");
                return false;
            }

        }
        else
        {
            return false; //nutzer war null

        }

}
/*
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

 */
    //Button registrieren
        /*
        registrieren.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        if(passwort.getText()!=null && nutzername.getText()!=null) {
                            String nuna = nutzernameV;
                            String pw = passwortV;
                            pw.trim();
                            nuna.trim();
                            Intent intent = new Intent(MainActivity.this, MyActivityRegistrierung.class);
                            intent.putExtra("Nutzername:", nuna);
                            intent.putExtra("Passwort:", pw);
                            startActivity(intent);


                        }
                        else
                        {

                            Intent intent=new Intent(MainActivity.this,MyActivityRegistrierung.class);

                        }




                    }
                }
        );
        //Button anmelden
        anmelden.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String nuna = nutzernameV;
                        String pw = passwortV;
                        boolean res;
                        //eingabe auslesen
                        res=auslesen(v);

                        if(res==true)
                        {
                            int ergebnis;
                            ergebnis=dbAbfrage(db,nutzernameV,passwortV);

                            if(ergebnis==1)
                            {
                                /*
                                Intent intent = new Intent(MainActivity.this, Map.class);
                                  intent.putExtra("Nutzername:", nuna);
                                  intent.putExtra("Passwort:", pw);



                            }
                            else if(ergebnis==2)
                            {
                                Toast.makeText(MainActivity.this, "Falsches Passwort!", Toast.LENGTH_SHORT).show();

                            }
                            else if(ergebnis==3)
                            {
                                Toast.makeText(MainActivity.this, "Nutzer nicht gefunden!", Toast.LENGTH_SHORT).show();
                            }
                        }



                            //startActivity(intent);
                    }
                }
        );
*/
}









