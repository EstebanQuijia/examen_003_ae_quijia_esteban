package com.example.demo.repositories

import com.example.demo.models.entities.InventoryRule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRuleRepository : JpaRepository<InventoryRule, Long> {
    // Método extra que pide el examen
    fun findByIsActiveTrue(): List<InventoryRule>
}