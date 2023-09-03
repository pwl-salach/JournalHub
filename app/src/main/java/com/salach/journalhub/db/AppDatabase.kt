package com.salach.journalhub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.salach.journalhub.db.daos.ChoreDao
import com.salach.journalhub.db.daos.GoalDao
import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.daos.ProductDao
import com.salach.journalhub.db.daos.ShoppingListDao
import com.salach.journalhub.db.daos.ShoppingListItemDao
import com.salach.journalhub.db.models.Chore
import com.salach.journalhub.db.models.Converters
import com.salach.journalhub.db.models.Goal
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.db.models.Product
import com.salach.journalhub.db.models.Schedule
import com.salach.journalhub.db.models.ShoppingList
import com.salach.journalhub.db.models.ShoppingListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [
        Chore::class,
        Note::class,
        Page::class,
        Product::class,
        ShoppingList::class,
        ShoppingListItem::class,
        Goal::class,
        Journal::class,
        Schedule::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: initializeInstance(context, scope).also { INSTANCE = it }
            }
        }

        private fun initializeInstance(context: Context, scope: CoroutineScope): AppDatabase{
            var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "internal.db"
                )
            // check if dev env
            instance = instance.addCallback(DevSetupCallback(scope))
            return instance.build()
        }
    }

    abstract val choreDao: ChoreDao
    abstract val productDao: ProductDao
    abstract val shoppingList: ShoppingListDao
    abstract val shoppingListItemDao: ShoppingListItemDao
    abstract val noteDao: NoteDao
    abstract val pageDao: PageDao
    abstract val goalDao: GoalDao
    abstract val journalDao: JournalDao

    private class DevSetupCallback(private val scope: CoroutineScope) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { appDatabase ->
                scope.launch {
                    appDatabase.choreDao.deleteAll()
                    appDatabase.noteDao.deleteAll()
                    appDatabase.pageDao.deleteAll()
                    appDatabase.journalDao.deleteAll()

//                    appDatabase.noteDao.insertAll(
//                        Note("Generic", ListIcon.HOME.id, 0, 1),
//                        Note("Initial", ListIcon.HOME.id, 0, 2),
//                        Note("Test", ListIcon.ALERT.id, 0, 3),
//                        Note("Test2", ListIcon.ALERT.id, 0, 4),
//                        Note("Test3", ListIcon.ALERT.id, 0, 5),
//                    )
//                    appDatabase.notePartDao.insertAll(
//                        Page(1, PageType.CHORE, 1),
//                        Page(1, PageType.CHORE, 2),
//                        Page(2, PageType.CHORE, 3),
//                        Page(2, PageType.NOTE, 4),
//                        Page(3, PageType.CHORE, 5),
//                        Page(4, PageType.NOTE, 6),
//                        Page(5, PageType.CHORE, 7),
//                    )
//                    appDatabase.choreDao.insertAll(
//                        Chore(1, "QWE"),
//                        Chore(2, "ASD"),
//                        Chore(3, "ZXC"),
//                        Chore(5, "RTY"),
//                        Chore(7, "RTY"),
//                    )
//                    appDatabase.memoDao.insertAll(
//                        Note(
//                            4,
//                            """A bit longer note.
//                                |Potentially multiline!!!
//                            """.trimMargin()
//                        ),
//                        Note(6, "dasdasdasdasda as dasd")
//                    )
//                    appDatabase.goalDao.insertAll(
//                        Goal(R.drawable.ic_launcher_foreground, "Abc", "small test", 0.69f, 0),
//                        Goal(R.drawable.ic_launcher_foreground, "HGCVH", "small test", 0.21f, 1),
//                        Goal(R.drawable.ic_launcher_foreground, "Test", "small test", 0.37f, 2)
//                    )
                }
            }
        }
    }
}
