package com.example.demo.models.dto

import java.time.LocalDateTime

data class InventoryRuleResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val isActive: Boolean,
    val updatedBy: String,
    val updatedAt: LocalDateTime
)