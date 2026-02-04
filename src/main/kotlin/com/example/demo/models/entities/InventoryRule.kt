package com.example.demo.models.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "inventory_rules")
class InventoryRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var isActive: Boolean = true,

    @Column(nullable = false)
    var updatedBy: String, // Aquí guardaremos el 'sub' de Cognito

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)