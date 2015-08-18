package com.example.yamauchi.fruitsorder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity
    implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 戻るボタンのクリックリスナー設定
        Button button = (Button)findViewById(R.id.bt_back);
        button.setTag("back");
        button.setOnClickListener(this);

        // 確認ボタンのクリックリスナー設定
        Button button2  = (Button)findViewById(R.id.bt_confirm);
        button2.setTag("confirm");
        button2.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            // 付加情報からの入力データ取得
            String name = extra.getString("NAME");
            String address = extra.getString("ADDRESS");
            String month = extra.getString("MONTH");
            String day = extra.getString("DAY");
            String gender = extra.getString("GENDER");
            String apple = extra.getString("APPLE");
            String orange = extra.getString("ORANGE");
            String peach = extra.getString("PEACH");

            // 出力用テキストオブジェクト取得
            TextView tvName = (TextView)findViewById(R.id.tv_name);
            TextView tvAddress = (TextView)findViewById(R.id.tv_address);
            TextView tvMonth = (TextView)findViewById(R.id.tv_month);
            TextView tvDay = (TextView)findViewById(R.id.tv_day);
            TextView tvGender = (TextView)findViewById(R.id.tv_gender);
            TextView tvApple = (TextView)findViewById(R.id.tv_apple);
            TextView tvOrange = (TextView)findViewById(R.id.tv_orange);
            TextView tvPeach = (TextView)findViewById(R.id.tv_peach);

            // 出力用テキストオブジェクトに入力データを設定
            tvName.setText(name);
            tvAddress.setText(address);
            tvMonth.setText(month);
            tvDay.setText(day);
            tvGender.setText(gender);
            tvApple.setText(apple);
            tvOrange.setText(orange);
            tvPeach.setText(peach);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // タグの取得
        String tag = (String)v.getTag();

        if(tag.equals("confirm")){
            Log.d("FruitsOrder", "確認ボタンクリック");

            // データベースに保存
            ContentValues vals = new ContentValues();
            vals.put("name", findViewById(R.id.tv_name).toString());
            vals.put("address", findViewById(R.id.tv_address).toString());
            vals.put("gendar", findViewById(R.id.tv_gender).toString());
            vals.put("apple", findViewById(R.id.tv_apple).toString());
            vals.put("orange", findViewById(R.id.tv_orange).toString());
            vals.put("peach", findViewById(R.id.tv_peach).toString());

            DBHelper dbh = new DBHelper(this);
            SQLiteDatabase db = dbh.getWritableDatabase();

            try{
                db.insert(DBHelper.TABLENAME, "", vals);
            }finally{
                db.close();
            }

            //  インテントの生成(呼び出すクラスの指定)
            Intent intent = new Intent(this, ThirdActivity.class);

            // 次のアクティビティの起動
            startActivity(intent);
        }else if(tag.equals("back")){
            Log.d("FruitsOrder", "戻るボタンクリック");
            finish();
        }
    }
}
