package com.tmns.kissplanner.kissplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class KissTable {

    public static final String KEY_NAME = "kiss_idea";
    public static final String KEY_DESCRIPTION = "kiss_desc";
    public static final String KEY_ROWID = "_id";

    private static final String TAG = "KissTable";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "kiss_db";
    private static final String DATABASE_TABLE = "kiss";
    private static final int DATABASE_VERSION = 3;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_NAME +" text not null, " + KEY_DESCRIPTION + " text not null);";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "Creating DataBase: " + DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public KissTable(Context ctx) {
        this.mCtx = ctx;
    }

    public KissTable open() throws SQLException {
        Log.i(TAG, "OPening DataBase Connection....");
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createKiss(String idea, String description) {
        Log.i(TAG, "Inserting record...");
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, idea);
        initialValues.put(KEY_DESCRIPTION, description);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

//    public boolean deleteEmployee(long rowId) {
//
//        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
//    }

    public Cursor fetchAllIdea() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_DESCRIPTION}, null, null, null, null, null);
    }

    public Cursor fetchIdea(long Id) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_DESCRIPTION}, KEY_ROWID + "=" + Id, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

//    public boolean updateEmployee(int empId, String empName, String empDesignation) {
//        ContentValues args = new ContentValues();
//        args.put(KEY_NAME, empName);
//        args.put(KEY_DESCRIPTION, empDesignation);
//
//        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + empId, null) > 0;
//    }

}