package com.kompasid.netdatalibrary.helpers


data class ValidateOSVersion(val major: Int, val minor: Int = 0, val patch: Int = 0) : Comparable<ValidateOSVersion> {
    companion object {
        fun parse(version: String): ValidateOSVersion {
            val parts = version.split(".").map { it.toIntOrNull() ?: 0 }
            return ValidateOSVersion(
                major = parts.getOrElse(0) { 0 },
                minor = parts.getOrElse(1) { 0 },
                patch = parts.getOrElse(2) { 0 }
            )
        }
    }

    override fun compareTo(other: ValidateOSVersion): Int {
        return compareValuesBy(this, other,
            { it.major },
            { it.minor },
            { it.patch }
        )
    }

    override fun toString(): String = "$major.$minor.$patch"
}
