package kr.or.dgit.sql_lite_study;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static kr.or.dgit.sql_lite_study.WordDbDao.COL_ENG;
import static kr.or.dgit.sql_lite_study.WordDbDao.COL_HAN;
import static kr.or.dgit.sql_lite_study.WordDbDao.COL_ID;

/**
 * Created by HKM on 2017-06-16.
 */

public class WordCursorAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    public WordCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        mInflater = LayoutInflater. from (context);
    }
    static class ViewHolder{
        TextView mId;
        TextView mEng;
        TextView mHan;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View itemLayout = mInflater.inflate(R.layout. dic_row , null);
        ViewHolder viewHolder = new ViewHolder();
        // 특정 필드의 인덱스값을 반환하며 , 필드가 존재하지 않을경우 예외를 발생시킵니다.
        viewHolder.mId =  (TextView) itemLayout.findViewById(R.id.dic_id );
        viewHolder.mEng = (TextView) itemLayout.findViewById(R.id.dic_eng );
        viewHolder.mHan = (TextView) itemLayout.findViewById(R.id.dic_han );
        itemLayout.setTag(viewHolder);
        return itemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        int idx = cursor.getInt(cursor.getColumnIndex( COL_ID ));
        String engStr = cursor.getString(cursor.getColumnIndex( COL_ENG ));
        String hanStr = cursor.getString(cursor.getColumnIndex( COL_HAN ));
        viewHolder.mId.setText(String. valueOf (idx));
        viewHolder.mEng.setText(engStr);
        viewHolder.mHan.setText(hanStr);
    }
}

