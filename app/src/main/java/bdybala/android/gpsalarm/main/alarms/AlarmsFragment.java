package bdybala.android.gpsalarm.main.alarms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import bdybala.android.gpsalarm.R;

public class AlarmsFragment extends MvpFragment<AlarmsView, AlarmsPresenter> implements AlarmsView {

    @Override
    public AlarmsPresenter createPresenter() {
        return new AlarmsPresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarms, container, false);
    }
}
