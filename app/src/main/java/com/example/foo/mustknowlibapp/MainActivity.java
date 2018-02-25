package com.example.foo.mustknowlibapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_name) TextView mEtName;
    @BindView(R.id.bt_save) Button mBtSave;
    @BindView(R.id.bt_next) Button mBtNext;
    @BindString(R.string.new_description) String mNewDescr;

    protected Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Realm.init(this);
        mRealm = Realm.getDefaultInstance();
    }

    @OnClick(R.id.bt_save)
    public void chnageText() {
        this.saveName();
    }

    @OnClick(R.id.bt_next)
    public void gotoUser() {
        Intent activity = UserActivity.newIntent(this);
        this.startActivity(activity);
    }

    protected void saveName() {
        String name = mEtName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        mRealm.beginTransaction();
        mRealm.deleteAll();
        User user = mRealm.createObject(User.class);
        user.setName(name);
        mRealm.commitTransaction();
    }
}
