package com.example.randybiglow.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    //static LinkedList<LinkedList<String>> outer = new LinkedList<>();
    //LinkedList<Integer> inner = new LinkedList<Integer>();
    //The comment above is an attempt at Persistence.

    //Saves space for the classes above
    LinkedList<String> mStringList;
    ArrayAdapter<String> mAdapter;
    EditText mEditText;
    Button mButton;

    //Overriding the method of this class.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Auto-generated by Android for onCreate method.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add a default list of lists.
        mStringList = new LinkedList<>();
        mStringList.add("Pay bills");
        mStringList.add("Groceries");
        mStringList.add("Laundry");

        //Defines the arbitrary names saved above.
        //Adapter is connected to the String of List.
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStringList);

        //The ListView, EditText, and Button is called and defined here.
        final ListView listName = (ListView) (findViewById(R.id.masterListView));
        listName.setAdapter(mAdapter);
        mEditText = (EditText) (findViewById(R.id.masterEditText));
        mButton = (Button) (findViewById(R.id.masterButton));

        //Allows for the user to click on the add button to enter a text.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Identifies the String as "input."
                String input = mEditText.getText().toString();
                if (input.length() == 0) {
                    //Users have to enter any character, an empty EditText will prompt the Add button to give the toast below.
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();

                }else {
                    //Any character with the click of the add button will prompt toast below.
                    Toast.makeText(MainActivity.this, "Hold to delete", Toast.LENGTH_LONG).show();
                    //Allows the input of any characters from the EditText to be added to the list.
                    mStringList.add(input);
                    //This is required every time any changes are made that used an adapter. Otherwise, it will not perform the data above.
                    mAdapter.notifyDataSetChanged();
                    //Empty quotes allows for the app to not crash if no character is present in the EditText before the add button is clicked.
                    mEditText.setText("");
                }

                //THIS IS AWESOME! This will hide the key board after user click on the add button. *Happy Dance*
                //The XML also has two settings to hide the key board when the app first launches.
                try {
                    InputMethodManager arbitraryName = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    arbitraryName.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
            }
        });

        //Setting onItemLongClick code to remove list items.
        listName.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //I am not sure why boolean is labeled in this method.
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //What is clicked will be removed and not any other list.
                mStringList.remove(position);
                mAdapter.notifyDataSetChanged();
                //The listener is constantly on, so when this action happens, then the code above performs.
                return true;
            }
        });

        listName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newIntent = new Intent(MainActivity.this, Main2Activity.class);
                newIntent.putExtra("newList", position);
                newIntent.putExtra("name", mStringList.get(position)); //Research mStringList.get(position).
                startActivity(newIntent);
            }
        });
    }
}
