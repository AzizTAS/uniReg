package com.engineersaint.uniReg.model

import jakarta.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val name: String?,

    @OneToMany(mappedBy = "course",  fetch = FetchType.LAZY)
    val students: Set<Student>? = emptySet()
)
{
    constructor() : this(null, null,null)
}