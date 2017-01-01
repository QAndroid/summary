package com.qandroid.summary;

import android.os.Bundle;
import android.view.View;

import com.qandroid.common.QBaseActivity;
import com.qandroid.summary.NameSpaceActivity;
import com.qandroid.summary.R;

public class SummaryActivity extends QBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
    }

    public void toNameSpace(View view) {
        toActivityByClass(NameSpaceActivity.class);
    }
}
