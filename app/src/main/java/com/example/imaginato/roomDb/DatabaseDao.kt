package com.example.imaginato.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DatabaseDao {

    //  Save Guest Email

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertEmail(guest : Email)

    @Query("Select * from Email WHERE userId =:userid")
    fun getUserName(userid: String) : LiveData<Email>


}
