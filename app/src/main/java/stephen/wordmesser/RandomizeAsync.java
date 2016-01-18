package stephen.wordmesser;

import android.os.AsyncTask;
import android.widget.EditText;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Stephen on 2015-11-25.
 */
public class RandomizeAsync extends AsyncTask<Void, Void, String> {
    String stringToRandomize;
    EditText fieldToPull;

    public RandomizeAsync(String startString, EditText field){
        this.stringToRandomize = startString;
        this.fieldToPull = field;
    }

    public String doInBackground(Void... params){
        Scanner ss = new Scanner(stringToRandomize);
        String wreckedString = "";
        while(ss.hasNext()) {
            String firstString = ss.next();
            String ruinedWord = "";
            Random rdm = new Random();
            int i = rdm.nextInt(firstString.length());
            int n = rdm.nextInt(firstString.length());
            if (i == n && n != firstString.length() - 1) {
                n = n + 1;
            }
            if (i == firstString.length() - 1) {
                i--;

            }
            try {
                if (n > i) {
                    ruinedWord = firstString.substring(0, i) + firstString.substring(n, n + 1) + firstString.substring(i + 1, n) + firstString.substring(i, i + 1) + firstString.substring(n + 1, firstString.length());

                } else {

                    ruinedWord = firstString.substring(0, n) + firstString.substring(i, i + 1) + firstString.substring(n + 1, i) + firstString.substring(n, n + 1) + firstString.substring(i + 1, firstString.length());
                }

                wreckedString += ruinedWord + " ";

            } catch(StringIndexOutOfBoundsException e){
                wreckedString += firstString + " ";

            }
        }
        return wreckedString;


    }

    public void onPostExecute(String randomizedStr){
        fieldToPull.setText(randomizedStr);

    }
}
