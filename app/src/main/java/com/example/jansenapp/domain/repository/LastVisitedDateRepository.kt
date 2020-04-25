package com.example.jansenapp.domain.repository

import com.example.jansenapp.data.local.dao.LastVisitedDateDao
import com.example.jansenapp.domain.model.LastVisitedDate
import java.util.*
import javax.inject.Inject


class LastVisitedDateRepository @Inject constructor(
    private val lastVisitedDateDao: LastVisitedDateDao
) {

    fun getLastVisitedDate(): LastVisitedDate? = lastVisitedDateDao.get()

    fun saveLastVisitedDate(){
        val date = Date()
        lastVisitedDateDao.save(LastVisitedDate(date))
    }

}