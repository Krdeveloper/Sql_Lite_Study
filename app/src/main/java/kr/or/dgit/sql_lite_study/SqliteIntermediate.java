package kr.or.dgit.sql_lite_study;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class SqliteIntermediate extends AppCompatActivity {
    EditText etInsEng;
    EditText etInsHan;
    EditText etUpNum;
    EditText etUpEng;
    EditText etUpHan;
    EditText etDelNum;

    ListView list;
    WordDbDao dao;
    WordCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_intermediate);

        etInsEng = (EditText)findViewById(R.id. etInsertEng );
        etInsHan = (EditText)findViewById(R.id. etInsertHan );
        etUpNum = (EditText)findViewById(R.id. etUpdateNum );
        etUpEng = (EditText)findViewById(R.id. etUpdateEng );
        etUpHan = (EditText)findViewById(R.id. etUpdateHan );
        etDelNum = (EditText)findViewById(R.id. etDeleteNum );

        dao = new WordDbDao(this);
        dao.open();

        Cursor cursor = dao.selectItemAll();
        //new int []{ R.id.dic_id , R.id.dic_eng , R.id.dig_han } - > dic_row.xml 에 정의된 id
        adapter = new WordCursorAdapter(
                this,
                R.layout. dic_row ,
                cursor,
                WordDbDao. selectcion ,
                new int[]{R.id.dic_id , R.id.dic_eng , R.id.dic_han } ,
                0
        );
        list= (ListView)findViewById(R.id.list );
        list.setAdapter(adapter);

    }

    public void mInsertClick(View view) {
        String newEng = etInsEng.getText().toString();
        String newHan = etInsHan.getText().toString();
        Dic newDic = new Dic(newEng, newHan);
        dao.insertItem(newDic);
        adapter.changeCursor(dao.selectItemAll());
        etInsEng.setText("");
        etInsHan.setText("");
    }

    public void mUpdateClick(View view) {
        int upId = Integer. parseInt (etUpNum.getText().toString());
        String upEng = etUpEng.getText().toString();
        String upHan = etUpHan.getText().toString();
        Dic upDic = new Dic(upId, upEng, upHan);
        dao.updateItem(upDic);
        adapter.changeCursor(dao.selectItemAll());
        etUpNum.setText("");
        etUpEng.setText("");
        etUpHan.setText("");

    }

    public void mDeleteClick(View view) {
        int delId = Integer. parseInt (etDelNum.getText().toString());
        dao.deleteItemById(delId);
        adapter.changeCursor(dao.selectItemAll());
        etDelNum.setText("");

    }

    @Override
    protected void onStop() {
        super.onStop();
        dao.close();
    }
}
