package com.chesire.zwei.room.converters

import androidx.room.TypeConverter
import com.chesire.zwei.xivapi.flags.Gender

class GenderConverter {
    @TypeConverter
    fun fromGenderToInt(gender: Gender): Int = gender.value

    @TypeConverter
    fun fromIntToGender(value: Int): Gender = Gender.getGenderForValue(value)
}
