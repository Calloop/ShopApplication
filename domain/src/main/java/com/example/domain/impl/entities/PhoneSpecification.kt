package com.example.domain.impl.entities

data class PhoneSpecification(
    val cpu: String,
    val camera: String,
    val memory: String,
    val ram: String,
    val colors: List<Int>,
    val capacity: List<Int>
)
