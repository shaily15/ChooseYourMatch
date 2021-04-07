package com.shaily.chooseyourmatch.db

import androidx.room.TypeConverter
import com.shaily.chooseyourmatch.models.*

class Converters {

    @TypeConverter
    fun fromName(firstName: Name): String {
        return firstName.first
    }

    @TypeConverter
    fun toName(firstName: String): Name {
        return Name(firstName, firstName, firstName)
    }

    @TypeConverter
    fun fromDob(age: Dob): Int? {
        return age.age
    }

    @TypeConverter
    fun toDob(age: Int): Dob {
        return Dob(age.toString(), age)
    }

    @TypeConverter
    fun fromId(id: Id): String {
        return id.name
    }

    @TypeConverter
    fun toId(id: String): Id {
        return Id(id, id)
    }

    @TypeConverter
    fun fromLocation(city: Location): String {
        return city.city + " " + city.state + " " + city.country
    }

    @TypeConverter
    fun toLocation(city: String): Location {
        return Location(city, city, city)
    }

    @TypeConverter
    fun fromPicture(picture: Picture): String {
        return picture.large
    }

    @TypeConverter
    fun toPicture(picture: String): Picture {
        return Picture(picture)
    }
}