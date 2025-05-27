package com.kompasid.netdatalibrary.base.validation

import io.konform.validation.Validation
import io.konform.validation.ValidationError

class Validator<T>(private val validation: Validation<T>) {
    fun validate(input: T): List<ValidationError>? {
        val result = validation(input)
        return if (result.errors.isNotEmpty()) result.errors else null
    }
}
