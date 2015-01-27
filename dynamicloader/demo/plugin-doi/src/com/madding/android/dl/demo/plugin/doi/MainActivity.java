package com.madding.android.dl.demo.plugin.doi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.madding.android.dl.acitivy.plugin.DLPluginActivity;
import com.madding.android.dl.demo.shared.api.SharedInterface;
import com.madding.android.dl.demo.shared.api.SharedInterfaceManager;

public class MainActivity extends DLPluginActivity {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        that.setContentView(generateContentView(that));
    }

    private View generateContentView(final Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        Button button = new Button(context);
        button.setText("Invoke host method");
        layout.addView(button, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedInterface hostInterface = SharedInterfaceManager.getSharedInterface();
                hostInterface.hostMethod(that);
            }
        });
        return layout;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult resultCode=" + resultCode);
        if (resultCode == RESULT_FIRST_USER) {
            that.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}