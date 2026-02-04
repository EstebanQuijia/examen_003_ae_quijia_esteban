package com.example.demo.controllers

import com.example.demo.models.dto.*
import com.example.demo.services.InventoryRuleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/rules")
class InventoryRuleController(private val service: InventoryRuleService) {

    // 1. Endpoint Público (Mover a un controlador aparte si prefieres /public/health)
    @GetMapping("/public/health")
    fun healthCheck(): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(mapOf(
            "status" to "OK",
            "timestamp" to LocalDateTime.now()
        ))
    }

    // 2. Endpoint Autenticado (Cualquier usuario con token JWT)
    @GetMapping
    fun getAll(): ResponseEntity<List<InventoryRuleResponse>> {
        return ResponseEntity.ok(service.getAllRules())
    }

    // 3. Endpoint Solo Administrador (Validado por el Service o SecurityConfig)
    @PostMapping
    fun create(@RequestBody request: InventoryRuleRequest): ResponseEntity<InventoryRuleResponse> {
        return ResponseEntity.ok(service.createRule(request))
    }

    @PatchMapping("/{id}/toggle")
    fun toggle(@PathVariable id: Long): ResponseEntity<InventoryRuleResponse> {
        return ResponseEntity.ok(service.toggleRule(id))
    }
}