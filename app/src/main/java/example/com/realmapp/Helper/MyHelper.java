package example.com.realmapp.Helper;

import android.content.Context;

import java.util.ArrayList;

import example.com.realmapp.Model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyHelper {

    Realm realm;
    RealmResults<User> user;

    public MyHelper(Realm realm) {
        this.realm = realm;

    }

    public void selectFromDB(){
        user = realm.where(User.class).findAll();

    }
    public ArrayList<User> justRefresh(){

        ArrayList<User> listitem = new ArrayList<>();
        for(User u : user){
            listitem.add(u);
        }

        return listitem;

    }
}
