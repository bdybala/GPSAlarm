package bdybala.android.gpsalarm.main;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter {

    @Override
    public void doShowMapsFragment() {
        getView().showMaps();
    }

    @Override
    public void doShowAlarmsFragment() {
        getView().showAlarms();
    }

    @Override
    public void doShowSettingsFragment() {
        getView().showMaps();
    }
}
