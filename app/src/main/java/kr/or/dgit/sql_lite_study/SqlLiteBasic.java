package kr.or.dgit.sql_lite_study;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SqlLiteBasic extends AppCompatActivity {
    WordDBHelper mHelper;
    TextView mTextView;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_basic);

        mHelper = WordDBHelper.getInstance(this);
        mTextView = (TextView) findViewById(R.id.tvResult);
        db = mHelper.getWritableDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

    public void mInsertClick(View view) {
        ContentValues row = new ContentValues();
        row.put("eng", "boy");
        row.put("han", "머스마");
        db.insert("dic", null, row);
        // SQL 명령으로 삽입 // db.execSQL ("INSERT INTO dic VALUES (null, 'girl', ' 가시나 ');");
        mTextView.setText("Insert Success");

    }

    public void mUpdateClick(View view) {
        ContentValues row = new ContentValues();
        row.put("han", "소년");
        db.update("dic", row, "eng=?", new String[]{"boy"});
        // SQL 명령으로 갱신 // db.execSQL ("UPDATE dic SET han = ' 소년 ' WHERE eng = 'boy';");
        mTextView.setText("Update Success");

    }

    public void mDeleteClick(View view) {
        db.delete("dic", null, null);
        // SQL 명령으로 삭제 // db.execSQL ("DELETE FROM dic ;");
        mTextView.setText("Delete Success");

    }

    public void mSelectClick(View view) {
        Cursor cursor = db.rawQuery("SELECT eng, han FROM dic", null);
        // query 메서드로 읽기
        // cursor = db.query (" dic ", new String[] {" eng ", " han "}, null,  null, null, null, null);
        // SQL 명령으로 읽기
        String Result = "";
        while(cursor.moveToNext()){
            String eng = cursor.getString(0);
            String han = cursor.getString(1);
            Result += (eng + " = " + han + "\n");

        }
        if (Result.length() == 0) {
            mTextView.setText("Empyt Set");
        } else {
            mTextView.setText(Result);
        }
        cursor.close();

    }
}
