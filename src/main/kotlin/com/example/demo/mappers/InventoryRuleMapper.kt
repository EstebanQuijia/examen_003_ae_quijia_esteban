package com.example.demo.mappers

import com.example.demo.models.entities.InventoryRule
import com.example.demo.models.dto.InventoryRuleRequest
import com.example.demo.models.dto.InventoryRuleResponse
import org.springframework.stereotype.Component

@Component
class InventoryRuleMapper {

    fun toEntity(request: InventoryRuleRequest): InventoryRule {
        return InventoryRule(
            name = request.name,
            description = request.description,
            isActive = request.isActive,
            updatedBy = "PENDING" // Se sobreescribe en el Service con el ID de Cognito
        )
    }

    fun toResponse(entity: InventoryRule): InventoryRuleResponse {
        return InventoryRuleResponse(
            id = entity.id ?: 0,
            name = entity.name,
            description = entity.description,
            isActive = entity.isActive,
            updatedBy = entity.updatedBy,
            updatedAt = entity.updatedAt
        )
    }
}