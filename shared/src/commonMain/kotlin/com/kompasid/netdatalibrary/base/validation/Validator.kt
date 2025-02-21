package com.kompasid.netdatalibrary.base.validation

import io.konform.validation.Validation
import io.konform.validation.ValidationError

// Generic Validator class
class Validator<T>(private val validation: Validation<T>) {

    suspend fun validate(input: T): List<ValidationError>? {
        val result = validation(input)
        return if (result.errors.isNotEmpty()) {
            result.errors
        } else {
            null
        }
    }
}