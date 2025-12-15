package com.teamforone.quanlysinhvien.data.db;

import android.util.Base64;

import com.teamforone.quanlysinhvien.BuildConfig;

import java.nio.charset.StandardCharsets;

public final class DatabaseProvider {

    private DatabaseProvider() {}

    /**
     * Lấy path file DB trong assets (ví dụ: "databases/QLSV.db")
     */
    public static String getAssetDbPath() {
        return new String(
                Base64.decode(BuildConfig.DB_NAME_ENCODED, Base64.NO_WRAP),
                StandardCharsets.UTF_8
        );
    }

    /**
     * Lấy tên file DB (ví dụ: "QLSV.db")
     */
    public static String getDbName() {
        String fullPath = getAssetDbPath();
        return fullPath.substring(fullPath.lastIndexOf('/') + 1);
    }
}
