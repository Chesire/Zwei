package com.chesire.zwei.xivapi.flags

enum class Gender(val value: Int) {
    Unknown(-1),
    Male(1),
    Female(2);

    companion object {
        fun getGenderForValue(value: Int): Gender {
            return Gender.values().find { it.value == value } ?: Unknown
        }
    }
}