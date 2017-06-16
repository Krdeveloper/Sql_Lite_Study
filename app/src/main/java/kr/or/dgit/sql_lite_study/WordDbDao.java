package kr.or.dgit.sql_lite_study;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HKM on 2017-06-16.
 */

public class WordDbDao {
    static final String TABLE_NAME = "dic";
    // 컬럼 이름
    static final String COL_ID = "_id";
    static final String COL_ENG = "eng";
    static final String COL_HAN = "han";
    //selection
    static final String[] selectcion =  new String[]{ COL_ID , COL_ENG , COL_HAN };

    private WordDBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    public WordDbDao(Context ctx) {
        mCtx = ctx;
    }
    public void open() throws SQLException {
        mDbHelper = WordDBHelper.getInstance (mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close(){
        if (mDbHelper != null){
            mDbHelper.close();
        }
    }

    public void insertItem(Dic item){
        ContentValues row = new ContentValues();
        row.put( COL_ENG , item.getEngStr());
        row.put( COL_HAN , item.getHanStr());
        mDb.insert( TABLE_NAME , null, row);
    }
    public void deleteItemById(int id){
        mDb.delete( TABLE_NAME , COL_ID + "=?", new String[]{String. valueOf (id)});
    }
    public void updateItem(Dic item){
        ContentValues row = new ContentValues();
        row.put( COL_HAN , item.getHanStr());
        row.put( COL_ENG , item.getEngStr());
        mDb.update( TABLE_NAME , row, COL_ID +"=?", new String[]{String. valueOf (item.getId())});
    }

    public Cursor selectItemAll(){
        Cursor mCursor = mDb.query( TABLE_NAME , selectcion , null,null,null,null,null);
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }




}
