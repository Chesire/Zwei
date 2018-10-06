package com.chesire.zwei.room.converters

import androidx.room.TypeConverter
import com.chesire.zwei.xivapi.flags.Race

class RaceConverter {
    @TypeConverter
    fun fromRaceToInt(race: Race): Int = race.value

    @TypeConverter
    fun fromIntToRace(value: Int): Race = Race.getRaceForValue(value)
}
