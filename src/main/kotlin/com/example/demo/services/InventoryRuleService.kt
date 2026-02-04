package com.example.demo.services

import com.example.demo.exceptions.NotFoundException
import com.example.demo.exceptions.UnauthorizedActionException
import com.example.demo.models.dto.*
import com.example.demo.repositories.InventoryRuleRepository
import com.example.demo.mappers.InventoryRuleMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InventoryRuleService(
    private val repository: InventoryRuleRepository,
    private val mapper: InventoryRuleMapper
) {
    // Función para obtener el "sub" (ID de usuario) del token JWT
    private fun getUserIdFromToken(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val jwt = authentication.principal as? Jwt
            ?: throw UnauthorizedActionException("No se encontró el ID de usuario en el token")
        return jwt.getClaimAsString("sub")
    }

    fun getAllRules(): List<InventoryRuleResponse> {
        return repository.findAll().map { mapper.toResponse(it) }
    }

    fun createRule(request: InventoryRuleRequest): InventoryRuleResponse {
        val entity = mapper.toEntity(request).apply {
            updatedBy = getUserIdFromToken() // Auditoría obligatoria
        }
        return mapper.toResponse(repository.save(entity))
    }

    fun toggleRule(id: Long): InventoryRuleResponse {
        val rule = repository.findById(id).orElseThrow {
            NotFoundException("No se encontró la regla con ID $id")
        }

        rule.isActive = !rule.isActive
        rule.updatedBy = getUserIdFromToken() // Auditoría obligatoria
        rule.updatedAt = LocalDateTime.now()

        return mapper.toResponse(repository.save(rule))
    }

}