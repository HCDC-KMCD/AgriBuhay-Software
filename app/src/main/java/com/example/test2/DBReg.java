package com.example.test2;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBReg {

    private DatabaseReference databaseReference;

    public DBReg() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Registration.class.getSimpleName());
    }

    public Task<Void> add(Registration reg){

        return databaseReference.push().setValue(reg);
    }
}
