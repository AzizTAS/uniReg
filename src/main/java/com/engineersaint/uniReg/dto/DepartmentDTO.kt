package com.engineersaint.uniReg.dto

data class DepartmentDTO(
    val id: Int?,
    val name: String?
)
{
    constructor() : this(null,null )
}