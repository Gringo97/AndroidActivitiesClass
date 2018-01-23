package fuente.oscar.milib.fragments.Register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fuente.oscar.milib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    RegisterFragmentEvents events;
    RegisterFragmentListener listener;



    TextView lblUserName,lblEmail, lblPassword;
    EditText txtUserName, txtPassword, txtEmail;
    Button  btnBack,btnRegisterOk;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public void  setListener(RegisterFragmentListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register,container,false);
    //TextView User
        lblUserName = (TextView) v.findViewById(R.id.lblUserNameRegister);
        lblUserName.setText(R.string.UserName);
    //TextView Email
        lblEmail = (TextView) v.findViewById(R.id.lblEmailRegister);
        lblEmail.setText(R.string.Email);

    //TextView Password
        lblPassword = (TextView) v.findViewById(R.id.lblPasswordRegister);
        lblPassword.setText(R.string.Password);
        //EditText UserName
        txtUserName = (EditText) v.findViewById(R.id.txtUserNameRegister);
        txtUserName.setHint(R.string.UserName);
     //EditText Email
        txtEmail = (EditText) v.findViewById(R.id.txtEmailRegister);
        txtEmail.setHint(R.string.Email);
     //EditText Password
        txtPassword = (EditText) v.findViewById(R.id.txtPasswordLogin);
        txtPassword.setHint(R.string.Password);
//INICIALIZATE EVENTS FOR SETTING ONCLICK TO BUTTONS
        events = new RegisterFragmentEvents(this);

    //Button Back
        btnBack = (Button) v.findViewById(R.id.btnBackRegister);
        btnBack.setText(R.string.Back);
        btnBack.setOnClickListener(events);
     //Button OK Register
        btnRegisterOk = (Button) v.findViewById(R.id.btnOkRegister);
        btnRegisterOk.setText(R.string.register);
        btnRegisterOk.setOnClickListener(events);











        return v;
    }

}



