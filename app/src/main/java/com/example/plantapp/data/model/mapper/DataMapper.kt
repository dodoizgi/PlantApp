package com.example.plantapp.data.model.mapper

import com.example.plantapp.data.model.CategoryEntity
import com.example.plantapp.data.model.DataCategory
import com.example.plantapp.data.model.DataQuestion
import com.example.plantapp.data.model.QuestionEntity
import com.example.plantapp.domain.model.DomainCategory
import com.example.plantapp.domain.model.DomainCategoryImage
import com.example.plantapp.domain.model.DomainQuestion

object DataMapper {

    fun DataCategory.toCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            id = this.id,
            name = this.name,
            title = this.title,
            rank = this.rank,
            imageUrl = this.image.url
        )
    }

    fun CategoryEntity.toCategory(): DomainCategory {
        val categoryImage = DomainCategoryImage(0, "", this.imageUrl)
        return DomainCategory(
            id = this.id,
            name = this.name,
            title = this.title,
            rank = this.rank,
            image = categoryImage
        )
    }

    fun DataQuestion.toQuestionEntity(): QuestionEntity {
        return QuestionEntity(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            image_uri = this.image_uri,
            uri = this.uri,
            order = this.order
        )
    }

    fun QuestionEntity.toQuestion(): DomainQuestion {
        return DomainQuestion(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            image_uri = this.image_uri,
            uri = this.uri,
            order = this.order
        )
    }

    fun List<DataCategory>.toDomainCategoryList(): List<DomainCategory> {
        return this.map { it.toDomainCategory() }
    }

    fun List<DataQuestion>.toDomainQuestionList(): List<DomainQuestion> {
        return this.map { it.toDomainQuestion() }
    }

    fun DataCategory.toDomainCategory(): DomainCategory {
        val categoryImage = DomainCategoryImage(0, "", this.image.url)
        return DomainCategory(
            id = this.id,
            name = this.name,
            title = this.title,
            rank = this.rank,
            image = categoryImage
        )
    }

    fun DataQuestion.toDomainQuestion(): DomainQuestion {
        return DomainQuestion(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            image_uri = this.image_uri,
            uri = this.uri,
            order = this.order
        )
    }
}