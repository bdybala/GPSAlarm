package bdybala.android.gpsalarm.main.maps;

import android.support.annotation.NonNull;
import android.text.Editable;

import com.google.android.gms.maps.model.Circle;
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
                view.showRadiusInputDialog();
            }
        });
    }

    @Override
    public void handleNewAlarmBtnClick(Circle mCircle, Editable radiusText) {
        ifViewAttached(new ViewAction<MapsView>() {
            @Override
            public void run(@NonNull MapsView view) {
                // TODO create new alarm
                view.hideRadiusInputDialog();
                view.clearCircle();
            }
        });
    }

    @Override
    public void handleClick(LatLng latLng) {
        ifViewAttached(new ViewAction<MapsView>() {
            @Override
            public void run(@NonNull MapsView view) {
                view.hideRadiusInputDialog();
                view.clearCircle();
            }
        });
    }
}
