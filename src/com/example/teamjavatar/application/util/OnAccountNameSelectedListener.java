package com.example.teamjavatar.application.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * A listener to change the account id of an object when selected.
 *
 * @author Brian
 *
 */
public class OnAccountNameSelectedListener implements OnItemSelectedListener {
    private AccountNameSettable t;

    public OnAccountNameSelectedListener(AccountNameSettable t) {
        this.t = t;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        t.setAccountName((String) parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}