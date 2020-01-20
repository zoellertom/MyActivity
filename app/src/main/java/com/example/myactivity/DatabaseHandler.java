package com.example.myactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB";

    //USER table scores
    private static final String TABLE_USER = "user";
    private static final String USER_ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORT = "passwort";
    private static final String ALTER = "alter";
    //private static final String POINT_ID="point_id";
    //  private static final String ROUTE_ID="route_id";
    private static final String TABLE_ACTIVITY="activity";
    private static final String ACTIVITY_ID="activityID";
    private static final String ACTIVITY_NAME="activityName";
    private static final String BESCHREIBUNG="beschreibung";
    private static final String BEWERTUNG="bewertung";
    private static final String XPOS="xpos";
    private static final String YPOS="ypos";


    public DatabaseHandler(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "create table " + TABLE_USER + "(" +
                USER_ID + " INTEGER PRIMARY KEY, " +
                NAME + " VARCHAR2(30) NOT NULL, " +
                EMAIL + " VARCHAR2(30), " +
                PASSWORT + " VARCHAR2(30) NOT NULL, " +
                ALTER + " INTEGER, " +
                //   POINT_ID + " INTEGER CONSTRAINT FOREIGN KEY, " +
                //   ROUTE_ID + " INTEGER CONSTRAINT FOREIGN KEY" +
                ");";

        String CREATE_ACTIVITY_TABLE = "create table " + TABLE_ACTIVITY + "(" +
                ACTIVITY_ID + " INTEGER PRIMARY KEY, " +
                ACTIVITY_NAME + " VARCHAR2(30) NOT NULL, " +
                BESCHREIBUNG + " VARCHAR2(30), " +
                BEWERTUNG + " INTEGER, " +
                XPOS + " VARCHAR2(30) NOT NULL, " +
                YPOS + " VARCHAR2(30) NOT NULL, " +
                //   POINT_ID + " INTEGER CONSTRAINT FOREIGN KEY, " +
                //   ROUTE_ID + " INTEGER CONSTRAINT FOREIGN KEY" +
                ");";





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USER + TABLE_ACTIVITY);
        onCreate(db); //Absegnen lassen : Kann ich hier einfach table-activity hinzufügen?
    }



    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getId());
        values.put(NAME, user.getName());
        values.put(ALTER, user.getAlter());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORT, user.getPasswort());
        //müssen hier noch die anderen Werte mit null gefüllt werden?

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getId());
        values.put(NAME, user.getName());
        values.put(ALTER, user.getAlter());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORT, user.getPasswort());
        //müssen hier noch die anderen Werte mit null gefüllt werden?
        return db.update(TABLE_USER, values, USER_ID + "=?", new String[]{String.valueOf(user.getId())});//warum geht das nicht?
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + "=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }


    public User getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_USER, new String[]{USER_ID, NAME, ALTER, EMAIL, PASSWORT}, NAME + "=?",
                new String[]{name}, null, null, null, null);
        User user = null;
        if (c.moveToFirst())


            user = new User(Integer.parseInt(c.getString(0)), c.getString(1), Integer.parseInt(c.getString(2)),
                    c.getString(3), c.getString(4));
        c.close();
        db.close();
        return (user);


    }

    //Funktionen Tabelle Activities

    public void addActivity(Activity activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTIVITY_ID, activity.getId());
        values.put(ACTIVITY_NAME, activity.getActivityName());
        values.put(BEWERTUNG,activity.getBewertung ());
        values.put(XPOS, activity.getXpos());
        values.put(YPOS, activity.getYpos());


        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public int updateActivtiy(Activity activity)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ACTIVITY_ID, activity.getId());
        values.put(ACTIVITY_NAME, activity.getActivityName());
        values.put(BEWERTUNG,activity.getBewertung());//vielleicht lieber rausnehmen
        values.put(XPOS,activity.getXpos());
        values.put(YPOS,activity.getYpos());

        return db.update(TABLE_ACTIVITY,values,ACTIVITY_ID + "=?", new String[] {String.valueOf(activity.getId())});
    }

    public int updateActivityBeschreibung(Activity activity,String beschreibung)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BESCHREIBUNG,activity.getBeschreibung());
        values.put(BESCHREIBUNG,beschreibung);

        return db.update(TABLE_ACTIVITY,values,ACTIVITY_ID +"=?", new String[] {String.valueOf(activity.getId())});
    }


    public void deleteActivtiy(Activity activity)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_ACTIVITY, ACTIVITY_ID + " =?", new String[]{String.valueOf(activity.getId())});
    }


    public Activity getActivity(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.query(TABLE_ACTIVITY, new String[]{ACTIVITY_ID,ACTIVITY_NAME,BEWERTUNG,BESCHREIBUNG,XPOS,YPOS}, ACTIVITY_NAME + "=?",
                new String[]{name}, null, null , null, null);
        Activity activity=null;
        if(c.moveToFirst())
            activity=new Activity(c.getString(1),
                    c.getDouble(4), c.getDouble(5),c.getString(3));
            c.close();
            db.close();
            return activity;


    }




}
