package com.example.teamjavatar.application.util;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * A listener to change the report type of an object when selected.
 *
 * @author Brian
 *
 */
public class OnReportTypeSelectedListener implements OnItemSelectedListener {
    private ReportTypeSettable t;

    public OnReportTypeSelectedListener(ReportTypeSettable t) {
        this.t = t;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        t.setReportType((String) parent.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}