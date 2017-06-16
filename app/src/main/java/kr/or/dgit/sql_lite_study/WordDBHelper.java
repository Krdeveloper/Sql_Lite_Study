package kr.or.dgit.sql_lite_study;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by HKM on 2017-06-15.
 */

public class WordDBHelper extends SQLiteOpenHelper{
    private static final String TAG = WordDBHelper.class.getSimpleName();
    private static final int SCHEMA_VERSION = 2;
    private static final String DB_NAME = "BasicEngWord.db";
    private final Context context;
    private static WordDBHelper instance ;

    public synchronized static WordDBHelper getInstance(Context context) {
        if ( instance == null){
            instance = new WordDBHelper(context.getApplicationContext());
        }
        return instance ;
    }

    private WordDBHelper(Context context){
        super(context, DB_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i=1; i<= SCHEMA_VERSION ; i++){
            applySqlFile(db, i, null);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = (oldVersion + 1); i<=newVersion; i++){
            applySqlFile(db, i, null);
        }
        applySqlFile(db, 0, "data.sql");
    }

    private void applySqlFile(SQLiteDatabase db, int version, String fileName){
        BufferedReader reader = null;
        String filename = fileName;
        if (filename==null) {
            filename = String. format ("%s.%d.sql", DB_NAME , version);
        }
        try {
            final InputStream inputStream = context.getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder statement = new StringBuilder();
            for(String line; (line = reader.readLine()) != null; ){
                if (!TextUtils. isEmpty (line) && !line.startsWith("--")){ // 빈문자열 무시
                     statement.append(line.trim());
                } if (line.endsWith(";")){
                    db.execSQL(statement.toString());
                    statement.setLength(0);
                }
            }
        } catch (IOException e) { Log. e ( TAG , "Could not apply SQL file -> " + e);
        } finally { if (reader != null){ try { reader.close(); } catch (IOException e) { Log. w ( TAG , "Could not close reader", e); }}}

    }
}
