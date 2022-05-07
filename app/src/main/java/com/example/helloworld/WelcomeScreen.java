package com.example.helloworld;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class WelcomeScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // this displays <- which will replace later
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        navDrawer = findViewById(R.id.navDrawer);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ProfileData profileData = null;
        if (bundle != null) {
            if (bundle.containsKey(Constants.NAME_KEY) && bundle.containsKey(Constants.AGE_KEY)
                    && bundle.containsKey(Constants.OCCUPATION_KEY)
                    && bundle.containsKey(Constants.DESCRIPTION_KEY)) {
                profileData = new ProfileData(bundle.getString(Constants.NAME_KEY),
                        bundle.getInt(Constants.AGE_KEY),
                        bundle.getString(Constants.OCCUPATION_KEY),
                        bundle.getString(Constants.DESCRIPTION_KEY));
            }
        }
        setupDrawerContent(navDrawer, profileData);

        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setProfileData(profileData);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, profileFragment).commit();
        setTitle(getString(R.string.profile));
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navigationView, ProfileData profileData) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem, profileData);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem, ProfileData profileData) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        switch(menuItem.getItemId()) {
            case R.id.profile_menu_item:
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setProfileData(profileData);
                fragment = profileFragment;
                break;
            case R.id.matches_menu_item:
                fragment = new MatchesFragment();
                break;
            case R.id.settings_menu_item:
                fragment = new SettingsFragment();
                break;
            default:
                ProfileFragment defaultProfileFragment = new ProfileFragment();
                defaultProfileFragment.setProfileData(profileData);
                fragment = defaultProfileFragment;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackClick(View view) {
        finish();
    }
}