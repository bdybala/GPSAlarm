package bdybala.android.gpsalarm.main.maps;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapsPresenterImpl extends MvpBasePresenter<MapsView> implements MapsPresenter {

    @Override
    public void handleLongClick(final LatLng point) {
        ifViewAttached(new ViewAction<MapsView>() {
            @Override
            public void run(@NonNull MapsView view) {
                view.drawCircleAndMoveThere(point, 750);
            }
        });
    }
}
