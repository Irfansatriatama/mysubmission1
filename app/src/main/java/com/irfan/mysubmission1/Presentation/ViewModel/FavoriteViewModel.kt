import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.mysubmission1.data.db.FavoriteData
import com.irfan.mysubmission1.data.db.FavoriteDatabase
import com.irfan.mysubmission1.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val db: FavoriteDatabase) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(db.favoriteDao())

    val successResult = MutableLiveData<Boolean>()

    fun setFavorite(item: FavoriteData) {
        viewModelScope.launch {
            val existingItem = mFavoriteRepository.findFavoriteById(item.id)

            if (existingItem != null) {
                mFavoriteRepository.delete(existingItem)
                successResult.postValue(false) // Item sudah ada, hapus favorit
            } else {
                mFavoriteRepository.insert(item)
                successResult.postValue(true) // Item ditambahkan ke favorit
            }
        }
    }

    suspend fun findFavorite(id: Int): FavoriteData? {
        return mFavoriteRepository.findFavoriteById(id)
    }

    fun update(favoriteData: FavoriteData) {
        viewModelScope.launch {
            mFavoriteRepository.update(favoriteData)
            successResult.postValue(true) // Update berhasil
        }
    }

    fun delete(favoriteData: FavoriteData) {
        viewModelScope.launch {
            mFavoriteRepository.delete(favoriteData)
            successResult.postValue(true) // Hapus berhasil
        }
    }

    fun loadAll() {
        // Menggunakan LiveData atau callback untuk mengembalikan data ke UI jika diperlukan
    }
}
