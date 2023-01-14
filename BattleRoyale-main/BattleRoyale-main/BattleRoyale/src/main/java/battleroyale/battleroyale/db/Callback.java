package battleroyale.battleroyale.db;

import java.sql.ResultSet;

public interface Callback {
    public void onQueryDone(ResultSet result);
}
