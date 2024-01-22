package com.example.examen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.*

@Entity(tableName = "lugares")
data class Lugar(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val visitOrder: Int,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val accommodationCost: Double,
    val transportCost: Double,
    val additionalComments: String
)

@Dao
interface LugarDao {
    @Insert
    suspend fun insertLugar(lugar: Lugar)

    @Query("SELECT * FROM lugares ORDER BY visitOrder")
    suspend fun getOrderedLugares(): List<Lugar>

    @Delete
    suspend fun deleteLugar(lugar: Lugar)
}

@Database(entities = [Lugar::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lugarDao(): LugarDao
}

class AppRepository(private val lugarDao: LugarDao) {
    suspend fun insertLugar(lugar: Lugar) {
        lugarDao.insertLugar(lugar)
    }

    suspend fun getOrderedLugares(): List<Lugar> {
        return lugarDao.getOrderedLugares()
    }

    suspend fun deleteLugar(lugar: Lugar) {
        lugarDao.deleteLugar(lugar)
    }
}

class LugarViewModel(private val repository: AppRepository) : ViewModel() {
    private val _lugares = MutableLiveData<List<Lugar>>()
    val lugares: LiveData<List<Lugar>> get() = _lugares

    fun getOrderedLugares() {
        viewModelScope.launch {
            _lugares.value = repository.getOrderedLugares()
        }
    }

    fun insertLugar(lugar: Lugar) {
        viewModelScope.launch {
            repository.insertLugar(lugar)
            getOrderedLugares()
        }
    }

    fun deleteLugar(lugar: Lugar) {
        viewModelScope.launch {
            repository.deleteLugar(lugar)
            getOrderedLugares()
        }
    }
}
