package bdybala.android.gpsalarm.main.maps;

import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface MapsView extends MvpView {

    void drawCircleAndMoveThere(LatLng latLng, int radius);

    void showRadiusInputDialog();

    void hideRadiusInputDialog();

    void moveToNewPoint(LatLng point);

    void clearCircle();
}
