package br.com.josef.marvelcharacters.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.josef.marvelcharacters.model.dao.ConverterDataClass
import br.com.josef.marvelcharacters.model.dao.MarvelResultDao
import br.com.josef.marvelcharacters.model.dataclass.MarvelResult


@Database(
    entities = [MarvelResult::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(ConverterDataClass::class)
abstract class MarvelDatabase : RoomDatabase() {
    abstract val marvelResultDao: MarvelResultDao

    companion object {

        @Volatile
        private var INSTANCE: MarvelDatabase? = null
        fun getInstance(context: Context): MarvelDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MarvelDatabase::class.java,
                        "marvel_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}