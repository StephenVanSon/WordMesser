package stephen.wordmesser;

import android.content.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Button;
import android.content.ClipboardManager;
import java.util.*;
import android.view.*;

public class MainActivity extends ActionBarActivity {
    boolean messedWith = false;
    String messerContent = "";
    String rdmContent = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void switchToRandomizer(View view){
        EditText et = (EditText) findViewById(R.id.editText);
        messerContent = et.getText().toString();
        setContentView(R.layout.randomizer);
        if(!rdmContent.equals("")){
            EditText edit = (EditText) findViewById(R.id.editText2);
            edit.setText(rdmContent);
        }

        //little easter egg
        if(messedWith == true){
            Button cp = (Button) findViewById(R.id.copyButton2);
            Button ps = (Button) findViewById(R.id.pasteButton1);
            String copy = cp.getText().toString();
            String paste = ps.getText().toString();

            String first = copy + " " + paste;
            MessWithWordAsync messWithWordAsync = new MessWithWordAsync(first,null,true);
            messWithWordAsync.execute();
            try {
                first = messWithWordAsync.get();
                copy = first.substring(0, first.indexOf(" "));
                paste = first.substring(first.indexOf(" ") + 1, first.length());
                cp.setText(copy);
                ps.setText(paste);
            } catch(Exception e){
                //something went wrong
            }
        }

    }


    public void switchToMesser(View view){
        EditText et  = (EditText) findViewById(R.id.editText2);
        rdmContent = et.getText().toString();
        setContentView(R.layout.activity_main);
        if(!messerContent.equals("")){
            EditText edit = (EditText) findViewById(R.id.editText);
            edit.setText(messerContent);

        }


    }

    public void copyRdm(View view){
        ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        EditText t =  (EditText) findViewById(R.id.editText2);
        String s = t.getText().toString();
        ClipData clip = ClipData.newPlainText("rdm", s);
        c.setPrimaryClip(clip);

    }

    public void copyMess(View view){
        ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        EditText t =  (EditText) findViewById(R.id.editText);
        String s = t.getText().toString();
        ClipData clip = ClipData.newPlainText("fck", s);
        c.setPrimaryClip(clip);
    }

    public void pasteMess(View view){
        ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        EditText t =  (EditText) findViewById(R.id.editText);
        ClipData clip = c.getPrimaryClip();
        ClipData.Item i = clip.getItemAt(0);
        String s =  i.getText().toString();
        t.setText(s);

    }
    public void pasteRdm(View view){
        ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        EditText t =  (EditText) findViewById(R.id.editText2);
        ClipData clip = c.getPrimaryClip();
        ClipData.Item i = clip.getItemAt(0);
        String s =  i.getText().toString();
        t.setText(s);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void rdmButtonPress(View view){
        EditText words = (EditText) findViewById(R.id.editText2);
        String rdm = words.getText().toString();
        //start background thread to randomize words
        RandomizeAsync randomizeTask = new RandomizeAsync(rdm, words);
        randomizeTask.execute();


    }




    public void messWithButtonPress(View view){
        EditText fieldOfString = (EditText) findViewById(R.id.editText);
        String stringToMessUp = fieldOfString.getText().toString();
        MessWithWordAsync messWithInBackground = new MessWithWordAsync(stringToMessUp, (View)fieldOfString, false);
        messWithInBackground.execute();



        if(messedWith == false) {
            messedWith = true;
        } else{
            messedWith = false;
        }

    }




}
