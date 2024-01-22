package com.example.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vacacion.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PLACES = "places";
    private static final String TABLE_PHOTOS = "photos";

    // Common column names
    private static final String KEY_ID = "id";

    // Places table columns
    private static final String KEY_NAME = "name";
    private static final String KEY_VISIT_ORDER = "visit_order";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_ACCOMMODATION_COST = "accommodation_cost";
    private static final String KEY_TRANSPORT_COST = "transport_cost";
    private static final String KEY_ADDITIONAL_COMMENTS = "additional_comments";

    // Photos table columns
    private static final String KEY_PLACE_ID = "place_id";
    private static final String KEY_PHOTO_URI = "photo_uri";

    private static final String CREATE_TABLE_PLACES = "CREATE TABLE " + TABLE_PLACES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT,"
            + KEY_VISIT_ORDER + " INTEGER,"
            + KEY_IMAGE_URL + " TEXT,"
            + KEY_LATITUDE + " REAL,"
            + KEY_LONGITUDE + " REAL,"
            + KEY_ACCOMMODATION_COST + " REAL,"
            + KEY_TRANSPORT_COST + " REAL,"
            + KEY_ADDITIONAL_COMMENTS + " TEXT"
            + ")";

    private static final String CREATE_TABLE_PHOTOS = "CREATE TABLE " + TABLE_PHOTOS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PLACE_ID + " INTEGER,"
            + KEY_PHOTO_URI + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLACES);
        db.execSQL(CREATE_TABLE_PHOTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);

        onCreate(db);
    }
}

