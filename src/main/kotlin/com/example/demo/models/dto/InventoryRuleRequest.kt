package com.example.demo.models.dto

data class InventoryRuleRequest(
    val name: String,
    val description: String?,
    val isActive: Boolean = true
)