package com.chesire.zwei.xivapi.flags

/**
 * Contains a list of states a character can be in.
 * XIVApi: http://xivapi.com/docs/Character#section-2
 */
enum class State(
    val xivApiName: String,
    val value: Int
) {
    None("STATE_NONE", 0),
    Adding("STATE_ADDING", 1),
    Cached("STATE_CACHED", 2),
    NotFound("STATE_NOT_FOUND", 3),
    Blacklist("STATE_BLACKLIST", 4),
    Private("STATE_PRIVATE", 5)
}
