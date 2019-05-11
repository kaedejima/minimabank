package kaedejima.io.minimabank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static java.lang.String.valueOf;

public class SetmoneyActivity extends AppCompatActivity {
    public EditText titleText;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmoney);
        pref=getSharedPreferences("倉庫", Context.MODE_PRIVATE);
        editor=pref.edit();
        titleText = (EditText)findViewById(R.id.titleEditText);
        int num = pref.getInt("設定金額",20000);
        titleText.setText(String.valueOf(num));
    }

    public void update(View view) {
        editor.putInt("設定金額",Integer.parseInt(titleText.getText().toString()));
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
