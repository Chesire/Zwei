package com.chesire.zwei.xivapi.flags

/**
 * Contains a list of races a character can be.
 */
enum class Race(val value: Int) {
    Unknown(-1),
    Hyur(1),
    Elezen(2),
    Lalafell(3),
    Miqote(4),
    Roegadyn(5),
    AuRa(6);

    companion object {
        fun getRaceForValue(value: Int) = Race.values().find { it.value == value } ?: Unknown
    }
}