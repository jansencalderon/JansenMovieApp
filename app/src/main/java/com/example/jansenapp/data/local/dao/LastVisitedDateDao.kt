package com.example.jansenapp.data.local.dao

import com.example.jansenapp.data.local.models.LastVisitedDateDto
import com.example.jansenapp.data.local.utils.saveToRealm
import com.example.jansenapp.domain.model.LastVisitedDate
import io.realm.Realm
import javax.inject.Inject

class LastVisitedDateDao @Inject constructor() {

    fun get(): LastVisitedDate? {
        val lastVisited = Realm.getDefaultInstance().where(LastVisitedDateDto::class.java)
            .findFirst()
        return lastVisited?.toLastVisitedDomain
    }

    fun save(
        data: LastVisitedDate
    ) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.delete(LastVisitedDateDto::class.java)
        realm.saveToRealm(LastVisitedDateDto(data))
        realm.commitTransaction()
        realm.close()
    }

}