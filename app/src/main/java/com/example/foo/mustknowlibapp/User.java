package com.example.foo.mustknowlibapp;

import io.realm.RealmObject;

/**
 * Created by foo on 2/24/18.
 */

public class User extends RealmObject {
    protected int mId;
    protected String mName;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}