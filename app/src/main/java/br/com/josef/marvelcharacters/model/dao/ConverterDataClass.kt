package br.com.josef.marvelcharacters.model.dao

import androidx.room.TypeConverter
import br.com.josef.marvelcharacters.model.dataclass.Thumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterDataClass {

    var gson = Gson()

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail): String {
        return gson.toJson(thumbnail)
    }

    @TypeConverter
    fun stringToThumbnail(data: String): Thumbnail {
        val listType = object : TypeToken<Thumbnail>() {
        }.type
        return gson.fromJson(data, listType)
    }
}