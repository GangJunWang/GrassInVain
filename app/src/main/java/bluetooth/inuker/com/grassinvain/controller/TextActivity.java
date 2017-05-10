package bluetooth.inuker.com.grassinvain.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;

public class TextActivity extends AppCompatActivity {

    private List<String> heheh = new ArrayList<>();
    private TextView start;
    private TextView last;

    private GoogleApiClient client;
    private LinkedList<String> lasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }

}
