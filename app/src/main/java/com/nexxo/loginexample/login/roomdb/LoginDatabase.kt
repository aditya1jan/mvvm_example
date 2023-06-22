package com.nexxo.loginexample.login.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexxo.loginexample.login.model.LoginModel

@Database(entities = [LoginModel::class], version = 1,exportSchema = false)
abstract class LoginDatabase: RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object{

        private const val DB_NAME = "login_database.db"
        @Volatile private var instance: LoginDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LoginDatabase::class.java,
            DB_NAME
        ).build()
    }
}