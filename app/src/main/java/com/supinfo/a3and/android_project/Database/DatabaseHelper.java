package com.supinfo.a3and.android_project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "todolistApp.db";
    public static final int DB_VERSION = 1;

    // Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_TODOLIST = "todolist";


    // USERS Table - column names
    public static final String COLUMN_USERID = "userId";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // TODOLIST Table - column names
    public static final String COLUMN_TODOLISTID = "todolistId";
    public static final String COLUMN_LASTUPDATE = "lastupdate";
    public static final String COLUMN_USERCREATOR = "usercreator";
    public static final String COLUMN_USERINVITED = "userinvited";
    public static final String COLUMN_TODO = "todo";

    // Table Create Statements
    // USERS table create statement
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " + COLUMN_FIRSTNAME + " TEXT, " +
            COLUMN_LASTNAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT )";

    //TODOLIST table create statement
    public static final String CREATE_TABLE_TODOLIST = "CREATE TABLE "
            + TABLE_TODOLIST + "(" + COLUMN_TODOLISTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_LASTUPDATE + " DATETIME, " + COLUMN_USERCREATOR + " INTEGER, " +
            COLUMN_USERINVITED + " INTEGER, " + COLUMN_TODO + " TEXT )";


    public DatabaseHelper(Context context) {
        super(context, DB_FILE_NAME, null,DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_TODOLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_USERS");
        db.execSQL("DROP TABLE IF EXISTS TABLE_TODOLIST");
        onCreate(db);
    }
}
