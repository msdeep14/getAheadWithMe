package model.room;

import model.common.DateRange;

public class RoomKey {
    private String keyId;
    private String barcode;
    private DateRange dateRange;
    private boolean isActive;
    private boolean isMaster;

    public boolean assignRoom() {
        return true;
    }
}
