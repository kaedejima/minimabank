package kaedejima.io.minimabank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    public Realm realm;
    public ListView listView;
    TextView sumTextView;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        listView = (ListView) findViewById(R.id.listView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        pref=getSharedPreferences("倉庫", Context.MODE_PRIVATE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Memo memo = (Memo) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("updateDate", memo.updateDate);
                startActivity(intent);
            }
        });
    }

    public void setMemoList() {
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        MemoAdapter adapter = new MemoAdapter(this, R.layout.layout_item_memo, items);
        listView.setAdapter(adapter);
        int sum1 = 0;
        for (Memo item : items) { //Kakucho-gata for loop
            sum1 += Integer.parseInt(item.title);
        }
        int setNum = pref.getInt("設定金額",20000);
        sumTextView.setText(String.valueOf(setNum-sum1));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMemoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void create(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void setmoney(View view){
        Intent intent = new Intent(this, SetmoneyActivity.class);
        startActivity(intent);
    }
}
