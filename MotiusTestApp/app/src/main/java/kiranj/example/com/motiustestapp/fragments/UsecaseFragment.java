package kiranj.example.com.motiustestapp.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import kiranj.example.com.motiustestapp.CustomAdapter;
import kiranj.example.com.motiustestapp.JSonParser;
import kiranj.example.com.motiustestapp.R;
import kiranj.example.com.motiustestapp.WebRequest;

/**
 * Created by kiranj91 on 25-07-2016.
 * Fragment class for Usecase tab.
 */
public class UsecaseFragment extends ListFragment {

    public String urlString = "https://www.motius.de/api/usecases";

    //Empty Constructor
    public UsecaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetUsecases().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usecase, container, false);
    }

    public class GetUsecases extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> usecaseList;
        ProgressDialog proDialog;
        Activity activity = getActivity();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(activity);
            proDialog.setMessage("Please wait...");
            proDialog.setCancelable(false);
            proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            WebRequest webrequest = new WebRequest();

            // Making a request to url and getting response.
            String jsonString = webrequest.makeWebServiceCall(urlString, WebRequest.GET);
            Log.d("JSon String: ", ": " + jsonString);
            usecaseList = new JSonParser().ParseJSON(jsonString);
            return null;
        }

        @Override
        protected void onPostExecute(Void requestResult) {
            super.onPostExecute(requestResult);
            //Dismissing the progress dialog.
            if (proDialog.isShowing())
                proDialog.dismiss();
            //A custom adapter to display the usecases.
            setListAdapter(new CustomAdapter(activity, usecaseList));

        }
    }

}
