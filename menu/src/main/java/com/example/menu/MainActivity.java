package com.example.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public   NavigationView navigationView;
    MainActivityEvents events;
    ImageView imagenPerfil;
    TextView descActividad,nombreMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        events = new MainActivityEvents(this);
        DataHolder.instance.fireBaseAdmin.setListener(this.events);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
/*
NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
View headerView = navigationView.getHeaderView(0);
navUsername = (TextView) headerView.findViewById(R.id.navUsername);
navUsernam.setText("Your Text Here");



navHeaderView= navigationView.inflateHeaderView(R.layout.nav_header_main);
tvHeaderName= (TextView) navHeaderView.findViewById(R.id.tvHeaderName);
tvHeaderName.setText("Saly");
 */

      navigationView = (NavigationView) findViewById(R.id.nav_view);
     View   navHeaderView= navigationView.inflateHeaderView(R.layout.nav_header_main);
     navHeaderView.findViewById(R.id.layoutLinearHeader);
        this.navigationView.setNavigationItemSelectedListener(this);
        //this.navigationView = (NavigationView) this.findViewById(R.id.nav_view);

        this.imagenPerfil =  navHeaderView.findViewById(R.id.imgPerfilMenu);
        this.nombreMenu = (TextView) navHeaderView.findViewById(R.id.txtxNameMenu);
        this.descActividad = (TextView) navHeaderView.findViewById(R.id.txtActividadDescrip);

        Log.v("TextFieldEmail",this.descActividad.toString());


        setDatos();


    }

    public void setDatos(){
        try {
            Log.v("Entrada set Dats",""+DataHolder.instance.fireBaseAdmin.mAuth.getUid());
            DataHolder.instance.fireBaseAdmin.downloadAndObserveBranch("Perfiles/"+DataHolder.instance.fireBaseAdmin.mAuth.getUid());
            nombreMenu.setText(DataHolder.instance.fireBaseAdmin.mAuth.getCurrentUser().getPhoneNumber());
            descActividad.setText(DataHolder.instance.fireBaseAdmin.mAuth.getCurrentUser().getEmail());
            StorageReference storageReference = DataHolder.instance.fireBaseAdmin.pathReference.child(DataHolder.instance.fireBaseAdmin.mAuth.getUid()).child("imgPerfil.jpg");
            Glide.with(this /* context */)
                    .using(new FirebaseImageLoader())
                    .load(storageReference)
                    .override(100, 200)
                    .into(this.imagenPerfil);


        }catch (Exception e){
           Log.v(" error imagen",""+e.toString());
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickButton1 (View v){
        Log.v("OSCAR","ESTAS PULSANDO EL BOTON OSCAR");
    }
    public void onClickButton2 (View v){
        Log.v("MANUE","ESTAS PULSANDO EL BOTON MANUE");

    }
}class MainActivityEvents implements  FireBaseAdminListener{
    MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void fireBaseAdminRegisterOK(Boolean blOk) {

    }

    @Override
    public void fireBaseAdminLoginOk(Boolean blOk) {

    }

    @Override
    public void fireBaseAdminbranchDownload(String branch, DataSnapshot dataSnapshot) {
        if (dataSnapshot!=null){
            if(branch == "Perfiles/"+DataHolder.instance.fireBaseAdmin.mAuth.getUid()){
                Users user= dataSnapshot.getValue(Users.class);
                DataHolder.instance.username = user.nombre.toString();
            }
        }else {
            Log.v("DataSnapshot null","");
        }

    Log.v("RESULTADO RAMA",""+branch+""+dataSnapshot.toString());
    }

    @Override
    public void fireBaseImageDownload() {

    }
}
