package com.example.foo.mustknowlibapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class UserActivity extends ListActivity {

    protected Realm mRealm;
    protected List<User> mUsers = new ArrayList<>();
    @BindView(android.R.id.list) ListView mLvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ButterKnife.bind(this);

        // show saved user info
        mRealm = Realm.getDefaultInstance();
        RealmResults<User> result = mRealm.where(User.class).findAll();
        int numRows = result.size();
        for (int i = 0; i < numRows; i++) {
            User user = result.get(i);
            mUsers.add(user);
        }

        ListAdapter adapter = new UserListAdapter(this, mUsers);
        this.setListAdapter(adapter);

        int i = 0;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, UserActivity.class);
    }

    protected class UserListAdapter extends ArrayAdapter<User> {
        protected Context mContext;
        protected List<User> mItems;
        public UserListAdapter(Context context, List items) {
            super(context, 0, items);
            mContext = context;
            mItems = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater() // LayoutInflater.from(mContext)
                        .inflate(R.layout.user_list_item, parent, false);
            }
            String name = mItems.get(position).getName();
            TextView tvName = convertView.findViewById(R.id.tv_name);
            tvName.setText(name);
            return convertView;
        }
    }
}
