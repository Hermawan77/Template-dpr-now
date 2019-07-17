package com.example.template_dpr_now;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteActivity extends AppCompatActivity {
    private static final String[] TEXT = new String[]{
            "Apple", "Banana", "Cupcake", "Donute"};

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

        String[] tmp = getResources().getStringArray(R.array.Text);

        AutoCompleteTextView editText = findViewById(R.id.autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, R.id.text_view_list_item, tmp);
        editText.setAdapter(adapter);

    }
}
