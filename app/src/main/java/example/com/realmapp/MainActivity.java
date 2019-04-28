package example.com.realmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.realmapp.CustomAdapter.CustomAdapter;
import example.com.realmapp.Helper.MyHelper;
import example.com.realmapp.Model.User;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    Realm realm;
    Button btnSave;
    RecyclerView rv;
    MyHelper helper;
    RealmChangeListener realmChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.btn_save);
        realm = Realm.getDefaultInstance();
        rv = findViewById(R.id.rv);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });

        helper = new MyHelper(realm);
        helper.selectFromDB();

        CustomAdapter adapter = new CustomAdapter(this,helper.justRefresh());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        reFresh();

    }
    private void saveData(){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxId = realm.where(User.class).max("user_id");
                int newKey = ( maxId == null) ? 1 : maxId.intValue()+1;

                User user = realm.createObject(User.class, newKey);
                user.setUser_name(editName.getText().toString());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });

    }
    private void reFresh(){
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                CustomAdapter adapter = new CustomAdapter(MainActivity.this,helper.justRefresh());
                rv.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
