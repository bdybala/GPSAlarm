package bdybala.android.gpsalarm.enums;

import android.text.TextUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AvailableFragments {
    MAPS("bdybala.android.gpsalarm.main.MainActivity.KEY_SHOW_MAPS"),
    ALARMS("bdybala.android.gpsalarm.main.MainActivity.KEY_SHOW_ALARMS"),
    SETTINGS("bdybala.android.gpsalarm.main.MainActivity.KEY_SHOW_SETTINGS");

    private String stringValue;

    public static AvailableFragments getByStringValue(String stringValue) {
        AvailableFragments valueToReturn = MAPS;
        if (!TextUtils.isEmpty(stringValue)) {
            for (AvailableFragments singleEnum : AvailableFragments.values()) {
                valueToReturn = singleEnum.getStringValue().equals(stringValue)
                        ? singleEnum : valueToReturn;
            }
        }
        return valueToReturn;
    }
}
