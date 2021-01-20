package com.example.fresherslivestudent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fresherslivestudent.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.compToolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.nav_drawerLayout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open_navigation_drawer, R.string.close_navigation_drawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.stdNavigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch (id){
                    case R.id.stdInternship :
                //        fragment = new InternshipFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.stdApplication :
                 //       fragment = new ApplicationFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.stdEditProf :
                   //     fragment = new EditProfFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.stdContactUs :
                  //      fragment = new ContactFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.stdAboutUs :
                 //       fragment = new AboutFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
              /*  if(sharedPrefManager.isCompLogin()){
                    sharedPrefManager.updateAdminLoginStatus(false);
                }
                startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));
                finish(); */
                Toast.makeText(this,"You Click Logout",Toast.LENGTH_SHORT).show();
                break;
        }
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}