package com.example.jansenapp.data.local.models

import com.example.jansenapp.domain.model.LastVisitedDate
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class LastVisitedDateDto() : RealmObject() {
    @PrimaryKey
    var lastVisitedString: String = ""
    var lastVisited: Date = Date()

    constructor(item: LastVisitedDate) : this() {
        lastVisitedString = item.lastVisited.toString()
        lastVisited = item.lastVisited
    }

    val toLastVisitedDomain: LastVisitedDate
        get() = LastVisitedDate(
            lastVisited = lastVisited
        )
}
