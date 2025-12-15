package com.teamforone.quanlysinhvien.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper {

    private final Context context;

    public DatabaseHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * Copy DB từ assets nếu chưa tồn tại
     */
    public void prepareDatabase() {
        File dbFile = context.getDatabasePath(DatabaseProvider.getDbName());
        if (dbFile.exists()) return;

        dbFile.getParentFile().mkdirs();

        try (
                InputStream is = context.getAssets()
                        .open(DatabaseProvider.getAssetDbPath());
                OutputStream os = new FileOutputStream(dbFile)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException("Copy database failed", e);
        }
    }

    /**
     * Mở DB (đảm bảo DB đã được copy)
     */
    public SQLiteDatabase openDatabase() {
        prepareDatabase();
        return SQLiteDatabase.openDatabase(
                context.getDatabasePath(DatabaseProvider.getDbName()).getPath(),
                null,
                SQLiteDatabase.OPEN_READWRITE
        );
    }
}
