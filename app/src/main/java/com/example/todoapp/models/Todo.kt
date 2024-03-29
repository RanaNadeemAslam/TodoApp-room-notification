package com.example.todoapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Todo(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var desc: String = "",
                var dueDate: Date = Date(),
                var priorty: Int = 0,
                var notification: Boolean = false,
                var notificationID: String = "")