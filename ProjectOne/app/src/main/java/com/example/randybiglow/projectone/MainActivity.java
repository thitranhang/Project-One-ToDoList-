package com.example.randybiglow.projectone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList<String> mStringList;
    ArrayAdapter<String> mAdapter;
    EditText mEditText;
    Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add a default list
        mStringList = new LinkedList<>();
        mStringList.add("Pay bills");
        mStringList.add("Groceries");
        mStringList.add("Laundry");

        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,new ArrayList<String>(mStringList));

        ListView listName = (ListView) (findViewById(R.id.masterListView));
        listName.setAdapter(mAdapter);
        mEditText = (EditText) (findViewById(R.id.masterEditText));
        mAddButton = (Button) (findViewById(R.id.addButton));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditText.getText().toString();
                if (input.length() > 0) {
                    mStringList.add(input);
                    mAdapter.notifyDataSetChanged();
                    mEditText.setText("");
                }
            }
        });
    }
}
