package kiranj.example.com.motiustestapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kiranj on 26-07-2016.
 */
public class JSonParser {

    public static ArrayList<HashMap<String, String>> ParseJSON(String json) {

        if (json != null) {
            try {
                ArrayList<HashMap<String, String>> usecaseList = new ArrayList<HashMap<String, String>>();
                JSONArray usecases = new JSONArray(json);

                for (int i = 0; i < usecases.length(); i++) {

                    JSONObject usecase = (JSONObject) usecases.get(i);
                    String title = usecase.getString("title");
                    String body = usecase.getString("body");

                    String formattedBody = format(body);
                    HashMap<String, String> usecaseMap = new HashMap<String, String>();

                    usecaseMap.put("title", title);
                    usecaseMap.put("body", formattedBody);

                    usecaseList.add(usecaseMap);
                }
                return usecaseList;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            Log.e("JSonParse", "No JSon Data received from HTTP Request");
            return null;
        }

    }

    //Method to format the body(remove the unnecessary tags)
    private static String format(String body) {

        final Pattern pattern = Pattern.compile("<p>(.+?)</p>");
        final Matcher matcher = pattern.matcher(body);
        matcher.find();
        return matcher.group(1).toString();
    }
}
