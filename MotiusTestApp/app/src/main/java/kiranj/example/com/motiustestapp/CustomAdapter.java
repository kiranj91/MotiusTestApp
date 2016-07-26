package kiranj.example.com.motiustestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kiranj on 26-07-2016.
 * Adapter class to list the title and body of the usecases.
 */
public class CustomAdapter extends BaseAdapter {

    public String[] title;
    public String[] body;
    public Context context;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Activity activity, ArrayList<HashMap<String, String>> usecaseList) {

        title = new String[usecaseList.size() + 1];
        body = new String[usecaseList.size() + 1];

        ArrayList<HashMap<String, String>> tempUsecaseList = usecaseList;
        context = activity;

        //Iterating the Hashmaps of Usecases and building string arrays for "title" and "body"
        for(int i = 0; i< usecaseList.size(); i++) {
            HashMap<String, String> tempData = usecaseList.get(i);
            title[i] = tempData.get("title");
            body[i] = tempData.get("body");
        }
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Holder class to hold title and body.
    public class Holder
    {
        TextView textViewTitle;
        TextView textViewBody;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.usecase_row, null);
        holder.textViewTitle = (TextView) rowView.findViewById(R.id.title);
        holder.textViewBody = (TextView) rowView.findViewById(R.id.body);
        holder.textViewTitle.setText(title[position]);
        holder.textViewBody.setText(body[position]);
        return rowView;
    }
}
