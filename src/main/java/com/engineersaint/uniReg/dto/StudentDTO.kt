package com.engineersaint.uniReg.dto

data class StudentDTO(
    val id: Int?,
    val name: String?,
    val surname: String?,
    val email: String?,
    val departmentId: Int?,
    val courseId: Int?
) {
    constructor() : this(null, null, null, null, null, null)
}