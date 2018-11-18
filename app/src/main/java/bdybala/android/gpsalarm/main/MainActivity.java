package bdybala.android.gpsalarm.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.HashMap;
import java.util.Map;

import bdybala.android.gpsalarm.R;
import bdybala.android.gpsalarm.enums.AvailableFragments;
import bdybala.android.gpsalarm.main.alarms.AlarmsFragment;
import bdybala.android.gpsalarm.main.maps.MapsFragment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private final String KEY_SHOW_ACTION = "bdybala.android.gpsalarm.main.MainActivity.KEY_SHOW_ACTION";

    private Map<Class<? extends Fragment>, MvpFragment> mFragments = new HashMap<>();

    protected DrawerLayout mDrawerLayout;

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        } else {
            log.error("onCreate: actionBar is null");
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView mNavView = findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(this);

        delegateIntent(getIntent());

        getLocationPermission();
    }

    private void delegateIntent(Intent intent) {
        String action = intent.getStringExtra(KEY_SHOW_ACTION);
        AvailableFragments fragmentToShow = AvailableFragments.getByStringValue(action);
        switch (fragmentToShow) {
            case MAPS:
                getPresenter().doShowMapsFragment();
                break;
            case ALARMS:
                getPresenter().doShowAlarmsFragment();
                break;
            case SETTINGS:
                getPresenter().doShowSettingsFragment();
        }
    }

    @Override
    public void showMaps() {
        Fragment fragment = mFragments.get(MapsFragment.class);
        if (fragment == null) {
            mFragments.put(MapsFragment.class, new MapsFragment());
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mFragments.get(MapsFragment.class))
                .commit();
    }

    @Override
    public void showAlarms() {
        Fragment fragment = mFragments.get(AlarmsFragment.class);
        if (fragment == null) {
            mFragments.put(AlarmsFragment.class, new AlarmsFragment());
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mFragments.get(AlarmsFragment.class))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_new_alarm:
                Intent intent = getIntent();
                intent.putExtra(KEY_SHOW_ACTION, AvailableFragments.MAPS.getStringValue());
                delegateIntent(intent);
                break;
            case R.id.nav_settings:
                intent = getIntent();
                intent.putExtra(KEY_SHOW_ACTION, AvailableFragments.SETTINGS.getStringValue());
                delegateIntent(intent);
            case R.id.nav_show_alarms:
                intent = getIntent();
                intent.putExtra(KEY_SHOW_ACTION, AvailableFragments.ALARMS.getStringValue());
                delegateIntent(intent);
                break;
        }
        return true;
    }

    private void getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            log.debug("getLocationRequest: permission granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocationPermission();
                } else {
                    Toast.makeText(this, R.string.permission_location_denied, Toast.LENGTH_SHORT).show();
                }
        }
    }
}
