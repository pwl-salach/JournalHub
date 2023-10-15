package com.salach.journalhub.db.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Range
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): Long? {
        return time?.toNanoOfDay()
    }

    @TypeConverter
    fun toLocalTime(value: Long?): LocalTime? {
        return value?.let { LocalTime.ofNanoOfDay(it) }
    }
    @TypeConverter
    fun annotatedStringToJson(annotatedString: AnnotatedString): String {
        val gson = Gson()
        return gson.toJson(annotatedString)
    }

    @TypeConverter
    fun jsonToAnnotatedString(json: String): AnnotatedString {
        val gson = GsonBuilder()
            .registerTypeAdapter(AnnotatedString::class.java, AnnotatedStringTypeAdapter())
            .create()
        val x =  gson.fromJson(json, AnnotatedString::class.java)
        return x
    }
}

class AnnotatedStringTypeAdapter : TypeAdapter<AnnotatedString>() {
    override fun write(out: JsonWriter, value: AnnotatedString) {}

    override fun read(`in`: JsonReader): AnnotatedString {
        val jsonObject = JsonParser().parse(`in`).asJsonObject
        val styles = mutableListOf<Range<SpanStyle>>()


        for(entry in jsonObject.getAsJsonArray("spanStylesOrNull")){
            if (entry.asJsonObject.get("start").asInt == entry.asJsonObject.get("end").asInt){
                continue
            }
            // TODO implement font size handling
            var style = entry.asJsonObject.getAsJsonObject("item")
            val loaded = SpanStyle(
                background = Color(style.get("background").asInt),
//                fontSize = style.get("fontSize").asInt.sp,
                fontSize = 14.sp,
                fontStyle = FontStyle(style.get("fontStyle").asJsonObject.get("value").asInt),
                fontWeight = FontWeight(style.get("fontWeight").asJsonObject.get("weight").asInt),
//                letterSpacing = style.get("letterSpacing").asInt.sp
                letterSpacing = 0.1.sp
            )
            styles.add(Range(
                    loaded,
                    entry.asJsonObject.get("start").asInt,
                    entry.asJsonObject.get("end").asInt,
                )
            )
        }
        return AnnotatedString(jsonObject.get("text").asString, styles)
    }
}

