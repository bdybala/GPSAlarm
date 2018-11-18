package bdybala.android.gpsalarm.main;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface MainPresenter extends MvpPresenter<MainView> {
    void doShowMapsFragment();

    void doShowAlarmsFragment();

    void doShowSettingsFragment();
}
