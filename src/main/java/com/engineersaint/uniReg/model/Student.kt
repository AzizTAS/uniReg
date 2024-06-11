package com.engineersaint.uniReg.model

import jakarta.persistence.*

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? ,
    val name: String?,
    val surname: String?,
    val email: String?,

    @ManyToOne(fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
    @JoinColumn(name = "department_id", nullable = false)
    val department: Department?,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course?
)
{
    constructor() : this(null, null,null,null,null,null)
}