package fuente.oscar.milib.fragments.Login;


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
public class LoginFragment extends Fragment{


    LoginFragmentEvents events;
    LoginFragmentListener listener;

    //VISUAL

    TextView lblEmail,lblPassword;
    EditText txtEmail,txtPassword;
    Button btnLogin,btnRegister;

    public LoginFragment() {
        // Required empty public constructor
    }

    //Metodo para indicar al fragment cual es su listener
    public void setListener(LoginFragmentListener listener){
        this.listener = listener;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Dado que métodos como findViewById pertenece a la clase AppCompatActivity necesitamos guardar el fragment en una View para invocarlo
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        //USADO EL FICHERO STIRNG.xml de la libreria en vez del del fichero

        //TextView Email
        lblEmail = (TextView) v.findViewById(R.id.lblEmailLogIn);
        lblEmail.setText(R.string.Email);

         //TextView Password
        lblPassword = (TextView) v.findViewById(R.id.lblPasswordLogIn);
        lblPassword.setText(R.string.Password);

         //EditText Email
        txtEmail = (EditText) v.findViewById(R.id.txtEmailLogIn);
        txtEmail.setHint(R.string.Email);

        //EditText Password
        txtPassword = (EditText) v.findViewById(R.id.txtPasswordLogin);
        txtPassword.setHint(R.string.Password);

        //Button Login
        btnLogin = (Button) v.findViewById(R.id.btnOkLogIn);
        btnLogin.setText(R.string.login);

        //Button Register
        btnRegister = (Button) v.findViewById(R.id.btnGoRegister);
        btnRegister.setText(R.string.register);

    //Creacion del Events pasandole como parametro el Fragment
        events = new LoginFragmentEvents(this);

    //Seteamos el Listener a los botones pasandole como parametro el events.
        btnLogin.setOnClickListener(events);
        btnRegister.setOnClickListener(events);


        return v;
    }




}

