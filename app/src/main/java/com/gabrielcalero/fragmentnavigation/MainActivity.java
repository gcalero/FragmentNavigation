package com.gabrielcalero.fragmentnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigationView = findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(this);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Doesn't have to be onBackPressed
                onBackPressed();
            }
        });

        mDrawerToggle.syncState();

        loadFragment(new HomeFragment(), false, true);
        syncNavigationMenus(R.id.homeFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.homeFragment:
                loadFragment(new HomeFragment(), false, true);
                break;
            case R.id.firstFragment:
                loadFragment(new HomeFragment(), false, true);
                loadFragment(new FirstFragment(), true, false);
                break;
            case R.id.playFragment:
                loadFragment(new HomeFragment(), false, true);
                loadFragment(new PlayFragment(), true, false);
                break;
        }
        mDrawerLayout.closeDrawer(mNavigationView);
        syncNavigationMenus(menuItem.getItemId());
        return true;
    }

    private void syncNavigationMenus(int itemId) {
        switch (itemId) {
            case R.id.homeFragment:
                mNavigationView.setCheckedItem(R.id.homeFragment);
                mBottomNavigationView.setVisibility(View.GONE);
                break;
            case R.id.arrows:
            case R.id.firstFragment:
            case R.id.second:
            case R.id.third:
                mNavigationView.setCheckedItem(R.id.arrows);
                setBottomNavigationItemChecked(R.id.arrows);
                mBottomNavigationView.setVisibility(View.VISIBLE);
                break;
            case R.id.play:
            case R.id.playFragment:
            case R.id.stop:
                mNavigationView.setCheckedItem(R.id.playFragment);
                setBottomNavigationItemChecked(R.id.playFragment);
                mBottomNavigationView.setVisibility(View.VISIBLE);
                break;
        }
        setActionBarTitle(itemId);
        mDrawerToggle.setDrawerIndicatorEnabled(itemId == R.id.homeFragment);
    }

    private void setActionBarTitle(int menuId) {
        switch (menuId) {
            case R.id.homeFragment:
                getSupportActionBar().setTitle(R.string.home);
                break;
            case R.id.arrows:
            case R.id.firstFragment:
                getSupportActionBar().setTitle(R.string.first);
                break;
            case R.id.second:
                getSupportActionBar().setTitle(R.string.second);
                break;
            case R.id.third:
                getSupportActionBar().setTitle(R.string.third);
                break;
            case R.id.play:
            case R.id.playFragment:
                getSupportActionBar().setTitle(R.string.play);
                break;
            case R.id.stop:
                getSupportActionBar().setTitle(R.string.stop);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (f != null && f instanceof HomeFragment) {
            mNavigationView.setCheckedItem(R.id.homeFragment);
            mBottomNavigationView.setVisibility(View.GONE);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
        } else {
            mBottomNavigationView.setVisibility(View.VISIBLE);
        }
    }


    private void setBottomNavigationItemChecked(int id) {
        Menu menu = mBottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == id) {
                item.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrows:
                loadFragment(new FirstFragment(), true, false);
                break;
            case R.id.second:
                loadFragment(new SecondFragment(), true, false);
                break;
            case R.id.third:
                loadFragment(new ThirdFragment(), true, false);
                break;
            case R.id. backToFirst:
                popupFragmentBackStack();
                break;
            case R.id.backToSecond:
                popupFragmentBackStack();
                break;
            case R.id.play:
                loadFragment(new StopFragment(), true, false);
                break;
            case R.id.stop:
                popupFragmentBackStack();
                break;
        }
        syncNavigationMenus(v.getId());
    }

    private void popupFragmentBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack, boolean popStack) {
        FragmentManager fm = getSupportFragmentManager();
        if (popStack) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
