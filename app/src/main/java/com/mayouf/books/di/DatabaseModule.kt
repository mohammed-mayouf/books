package com.mayouf.books.di

import android.content.Context
import androidx.room.Room
import com.mayouf.books.data.local.BooksDao
import com.mayouf.books.data.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            "books_db"
        ).build()
    }

    @Provides
    fun provideBooksDao(db: BooksDatabase): BooksDao {
        return db.booksDao()
    }
}
