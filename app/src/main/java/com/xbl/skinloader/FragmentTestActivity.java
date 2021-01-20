package com.xbl.skinloader;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xbl.skinlib.base.BaseFragmentActivity;

public class FragmentTestActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment);
        if(fragment == null){
            fragment = ArticleListFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }
}
