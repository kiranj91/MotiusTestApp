package kiranj.example.com.motiustestapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kiranj.example.com.motiustestapp.R;

/**
 * Created by kiranj on 25-07-2016.
 * Fragment class for Blank Tab.
 */
public class BlankFragment extends Fragment {

    //Empty Constructor
    public BlankFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usecase, container, false);
    }

}
