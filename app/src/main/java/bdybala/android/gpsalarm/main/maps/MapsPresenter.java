package bdybala.android.gpsalarm.main.maps;

import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface MapsPresenter extends MvpPresenter<MapsView> {

    void handleLongClick(LatLng point);
}
