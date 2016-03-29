package db;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public final class GameMetaData {
    private GameMetaData() {
    }
    public static abstract class GamePlayer implements BaseColumns{
        public static final String TABLE_NAME = "player_table";
        public static final String PLAYER = "player";
        public static final String SCORE = "score";
        public static final String LEVEL = "level";
    }
}
