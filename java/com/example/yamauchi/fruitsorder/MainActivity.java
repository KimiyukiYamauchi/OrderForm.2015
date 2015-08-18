package com.example.yamauchi.fruitsorder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity
    implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button)findViewById(R.id.bt_send);
        button1.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // 送信ボタンが押された時の処理
    public void onClick(View v) {

        // 確認画面のインテントを作成
        Intent intent = new Intent(this, SecondActivity.class);

        // 名前の取得
        EditText name = (EditText)findViewById(R.id.et_name);

        // 住所の取得
        EditText address = (EditText)findViewById(R.id.et_address);

        // 生年月日の取得
        Spinner month = (Spinner)findViewById(R.id.sp_month);
        Spinner day = (Spinner)findViewById(R.id.sp_day);

        // 性別の取得
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_gender);
        RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());

        // りんご、みかん、ももの注文数量の取得
        CheckBox cb = (CheckBox)findViewById(R.id.cb_apple);
        EditText apple;
        if(cb.isChecked()){
            apple = (EditText)findViewById(R.id.et_apple);
        }else{
            apple = null;
        }
        cb = (CheckBox)findViewById(R.id.cb_apple);
        EditText orange;
        if(cb.isChecked()){
            orange = (EditText)findViewById(R.id.et_apple);
        }else{
            orange = null;
        }
        cb = (CheckBox)findViewById(R.id.cb_apple);
        EditText peach;
        if(cb.isChecked()){
            peach = (EditText)findViewById(R.id.et_apple);
        }else{
            peach = null;
        }

        // 入力データをインテントに設定
        intent.putExtra("NAME", name.getText().toString());

        intent.putExtra("ADDRESS", address.getText().toString());

        intent.putExtra("MONTH", month.getSelectedItem().toString());
        intent.putExtra("DAY", day.getSelectedItem().toString());

        intent.putExtra("GENDER", rb.getText().toString());

        if(apple != null){
            intent.putExtra("APPLE",  apple.getText().toString());
        }else{
            intent.putExtra("APPLE", "------");
        }
        if(orange != null){
            intent.putExtra("ORANGE",  orange.getText().toString());
        }else{
            intent.putExtra("ORANGE", "------");
        }
        if(peach != null){
            intent.putExtra("PEACH",  peach.getText().toString());
        }else{
            intent.putExtra("PEACH", "------");
        }



        // 確認画面に遷移
        startActivity(intent);
    }
}
