package com.zura.mysuperapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zura.mysuperapp.api.dto.Resource
import com.zura.mysuperapp.database.daos.ResourceDao

@Database(entities = [Resource::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getResourcesDao(): ResourceDao
}