package fuente.oscar.milib.fragments.Register;

import android.util.Log;
import android.view.View;

import fuente.oscar.milib.fragments.Register.RegisterFragment;

public class  RegisterFragmentEvents implements  View.OnClickListener{
    private RegisterFragment registerFragment;
    public RegisterFragmentEvents(RegisterFragment fragment){
        registerFragment = fragment;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.registerFragment.btnBack.getId()){
            this.registerFragment.listener.BackToLoginButtonCliked();
            Log.v("---->", "RegisterBtnBackClicked");
        } else if (v.getId() == this.registerFragment.btnRegisterOk.getId()){
            this.registerFragment.listener.RegisterAcceptButtonClicked(registerFragment.txtEmail.getText().toString(),registerFragment.txtPassword.getText().toString());
            Log.v("---->", "RegisterBtnRegisterAcceptCliked");
        }
    }
}
