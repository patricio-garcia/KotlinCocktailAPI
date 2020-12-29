package cl.serlitoral.kotlincocktailapi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.serlitoral.kotlincocktailapi.data.model.DrinkEntity
import cl.serlitoral.kotlincocktailapi.domain.data.iDrinkDAO

@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun drinkDAO(): iDrinkDAO

    //Se crea un singleton
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            //Room se puede utlizar con una base de datos local con:
            // .databaseBuilder(context.applicationContext, AppDatabase::class.java, "drink_table")
            //o solo mantenerla en memoria (sin persistencia) con:
            // .inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
            INSTANCE = INSTANCE ?: Room
                .databaseBuilder(context.applicationContext, AppDatabase::class.java, "drink_table")
                .build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}