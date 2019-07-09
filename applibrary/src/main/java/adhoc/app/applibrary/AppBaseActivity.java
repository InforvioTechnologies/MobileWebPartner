package adhoc.app.applibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import adhoc.app.applibrary.Config.AppUtils.Objs;

public class AppBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public void initAppCode() {
       initTools(R.string.app_name);
    }

    public void initTools(int id) {
        Objs.a.iniToolbar(this,id);
        Objs.a.initNavView(this);
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
        getMenuInflater().inflate(R.menu.app_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_dashboard) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        OnNavItemSelected(this,item);
        return true;
    }

    public void OnNavItemSelected(AppCompatActivity activity, MenuItem item) {
        Context mCon = activity;
        int id = item.getItemId();



        DrawerLayout drawer = (DrawerLayout) activity.findViewById(adhoc.app.applibrary.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
