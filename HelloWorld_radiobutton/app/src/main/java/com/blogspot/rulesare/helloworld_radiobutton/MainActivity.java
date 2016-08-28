package com.blogspot.rulesare.helloworld_radiobutton;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String[] stringUserDefined = new String[1];
        final TextView editText;
        final Button button_upper;
        final Button button_lower;
        final Button button_mixing;
        final Button button_random;
        final Button button_none;

        final RadioGroup radiogroup_main;
        radiogroup_main = (RadioGroup) findViewById(R.id.radioGroup_main);

        // for radio button
        button_upper = (Button) findViewById(R.id.radioButton_upper);
        button_lower = (Button) findViewById(R.id.radioButton_lower);
        button_mixing = (Button) findViewById(R.id.radioButton_mixing);
        button_random = (Button) findViewById(R.id.radioButton_random);
        button_none = (Button) findViewById(R.id.radioButton_none);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // for Plain Text which User type in
        editText = (TextView) findViewById(R.id.editText);
        stringUserDefined[0] = editText.getText().toString();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // determine if it's modified by User or by other buttons
                // bad move
                //if (editText.isFocused()){
                //    Log.d("editText", "onTextChanged  editText.isFocused()");
                //    stringUserDefined[0] = editText.getText().toString();
                //}
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null == editText.getTag()){
                    Log.d("editText", "afterTextChanged, by user input");
                    stringUserDefined[0] = editText.getText().toString();
                    // radiogroup_main.clearCheck();
                    // [bad move] button_none.setChecked(true);
                    radiogroup_main.check(R.id.radioButton_none);
                }
                else{
                    Log.d("editText", "afterTextChanged, by action of button");
                }
            }
        });


        button_upper.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                editText.setTag("button_upper");
                editText.setText(stringUserDefined[0].toUpperCase());
                editText.setTag(null);
            }
        });
        button_lower.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                editText.setTag("button_lower");
                editText.setText(stringUserDefined[0].toLowerCase());
                editText.setTag(null);
            }
        });
        button_mixing.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                char[] chars = stringUserDefined[0].toCharArray();
                boolean isUpper = true;
                for (int i=0; i<chars.length; i++){
                    if (Character.isLetter(chars[i])){
                        if (isUpper){
                            chars[i] = Character.toUpperCase(chars[i]);
                            isUpper = false;
                        }
                        else {
                            chars[i] = Character.toLowerCase(chars[i]);
                            isUpper = true;
                        }
                        Log.d("button_mixing", "converted to: " + chars[i]);
                    } // else: untouched
                }
                String newString = new String(chars);
                editText.setTag("button_mixing");
                editText.setText(newString);
                editText.setTag(null);
            }
        });
        button_random.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                char[] chars = stringUserDefined[0].toCharArray();
                Random toolkit = new Random();
                for (int i=0; i<chars.length; i++){
                    if (Character.isLetter(chars[i])){
                        if (toolkit.nextBoolean()){
                            chars[i] = Character.toUpperCase(chars[i]);
                        }
                        else {
                            chars[i] = Character.toLowerCase(chars[i]);
                        }
                        Log.d("button_random", "converted to: " + chars[i]);
                    } // else: untouched
                }
                String newString = new String(chars);
                editText.setTag("button_random");
                editText.setText(newString);
                editText.setTag(null);
            }
        });
        button_none.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                editText.setTag("button_none");
                editText.setText(stringUserDefined[0]);
                editText.setTag(null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
