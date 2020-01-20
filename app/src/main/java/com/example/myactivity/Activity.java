package com.example.myactivity;

public class Activity {
    /*
    ACTIVITY_ID + " INTEGER PRIMARY KEY, " +
    ACTIVITY_NAME + " VARCHAR2(30) NOT NULL, " +
    BESCHREIBUNG + " VARCHAR2(30), " +
    BEWERTUNG + " INTEGER, " +
    XPOS + " VARCHAR2(30) NOT NULL, " +
    YPOS + " VARCHAR2(30) NOT NULL, " +

     */

    private int id;
    private static int laufnummer=0;
    private String activityName;
    private String beschreibung;
    private int bewertung;
    private double xpos; //was ist das?
    private double ypos;
    private String madeByUserID;

    public String getMadeByUserID() {
        return madeByUserID;
    }

    public void setMadeByUserID(String madeByUserID) {
        this.madeByUserID = madeByUserID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getBewertung() {
        return bewertung;
    }

    public void setBewertung(int bewertung) {
        this.bewertung = bewertung;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    public double getXpos() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public Activity(String activityName, double xpos, double ypos)
    {
        this.activityName=activityName;
        this.xpos=xpos;
        this.ypos=ypos;
        laufnummer++;
        this.id=laufnummer;
    }
    public Activity(String activityName, double xpos, double ypos, String beschreibung)
    {
        this.activityName=activityName;
        this.xpos=xpos;
        this.ypos=ypos;
        this.beschreibung=beschreibung;
        laufnummer++;
        this.id=laufnummer;
    }

    //Funktion zur Bewertung hier einfügen oder in der DB sinnvoller?
    public Activity activityBewerten(int bewertung, Activity activity)
    {
        if(activity!=null) {
            activity.bewertung = bewertung;
            return activity;
        }
        else
        {
            return activity;
        }
    }

}
