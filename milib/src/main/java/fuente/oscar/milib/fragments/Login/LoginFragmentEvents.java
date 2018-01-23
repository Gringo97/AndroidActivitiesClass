package fuente.oscar.milib.fragments.Login;

import android.view.View;

import fuente.oscar.milib.fragments.Login.LoginFragment;

public  class  LoginFragmentEvents implements View.OnClickListener {

    private LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment fragmentEvents){
        this.loginFragment = fragmentEvents;
    }
    @Override
    public void onClick(View v) {
        //Si la View
        if(v.getId() == this.loginFragment.btnLogin.getId()){

            this.loginFragment.listener.LoginButtonClicked(this.loginFragment.txtEmail.getText().toString(),this.loginFragment.txtPassword.getText().toString());
        }else if(v.getId() == this.loginFragment.btnRegister.getId()){
            this.loginFragment.listener.RegisterButtonClicked();
        }
    }
}
