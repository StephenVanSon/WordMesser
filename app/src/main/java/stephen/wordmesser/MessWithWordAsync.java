package stephen.wordmesser;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;

import java.util.Scanner;

/**
 * Created by Stephen on 2015-11-25.
 */
public class MessWithWordAsync extends AsyncTask<Void, Void, String> {
    String sentenceToMess;
    View editingBox;
    boolean viewIsButton;

    public MessWithWordAsync(String sentence, View curView, boolean viewIsBtn){
        this.sentenceToMess = sentence;
        this.editingBox = curView;
        this.viewIsButton = viewIsBtn;
    }

    @Override
    public String doInBackground(Void... params){
        String wreckedString = "";
        Scanner ss = new Scanner(sentenceToMess);
        while (ss.hasNext()) {
            String firstString = ss.next();
            if(ss.hasNext()){
                String nextString = ss.next();

                String fL1 = firstString.substring(0, 1);
                String fL2 = nextString.substring(0, 1);
                firstString = fL2 + firstString.substring(1);
                nextString = fL1 + nextString.substring(1);
                wreckedString += firstString + " " + nextString + " ";
            }
            else{

                wreckedString+= firstString.substring(firstString.length() -1) + firstString.substring(1,firstString.length()-1) + firstString.substring(0,1);

            }

        }

      return wreckedString;

    }

    //set the editText to the wrecked string
    @Override
    public void onPostExecute(String wreckedString){
        //check if its an edit text or a button
        if(!viewIsButton) {
           EditText et =  (EditText) editingBox;
            et.setText(wreckedString);
        }


    }
}
