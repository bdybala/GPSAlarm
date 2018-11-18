package bdybala.android.gpsalarm.main.maps;

import android.text.Editable;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface MapsPresenter extends MvpPresenter<MapsView> {

    void handleLongClick(LatLng point);

    void handleNewAlarmBtnClick(Circle mCircle, Editable radiusText);

    void handleClick(LatLng latLng);
}
