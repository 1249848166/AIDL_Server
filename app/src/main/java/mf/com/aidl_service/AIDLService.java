package mf.com.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mf.com.aidl_service.model.Person;

public class AIDLService extends Service {

    List<Person> list;
    SharedPreferences sp;

    IBinder binder=new AIDL.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            list.add(person);
            SharedPreferences sp=getSharedPreferences("people",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(person.getName(),person.getName());
            editor.apply();
        }

        @Override
        public List<Person> getPersons() throws RemoteException {
            return list;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        list=new ArrayList<>();
        Log.e("binder","绑定成功");
        return binder;
    }
}
