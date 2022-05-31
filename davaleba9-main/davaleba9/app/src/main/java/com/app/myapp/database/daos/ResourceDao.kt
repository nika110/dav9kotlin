package com.zura.mysuperapp.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zura.mysuperapp.api.dto.Resource

@Dao
interface ResourceDao {
    @Query("select * from Resources")
    fun getAllResources(): List<Resource>

    @Insert
    fun insert(vararg resources: Resource)

    @Query("delete from Resources")
    fun clear()

    @Query("delete from Resources where Id not in (select Id from Resources limit 3)")
    fun deleteExceptTopThree()
}