package kiranj.example.com.motiustestapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kiranj.example.com.motiustestapp.R;

/**
 * Created by kiranj on 25-07-2016.
 * Fragment class for the Login tab.
 */
public class LoginFragment extends Fragment {

    public Button loginButton;
    public EditText username;
    public EditText password;

    //Empty Constructor
    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        loginButton = (Button) loginView.findViewById(R.id.btn_login);
        username = (EditText) loginView.findViewById(R.id.input_username);
        password = (EditText) loginView.findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Hello Motius!!", Toast.LENGTH_LONG).show();
            }
        });
        return loginView;
    }

}
