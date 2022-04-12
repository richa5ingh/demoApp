package com.astropics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astropics.db.entity.PicEntity
import com.astropics.di.DBRepository
import com.astropics.di.Transformer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val dbRepository: DBRepository) :
    ViewModel() {

    private val _favList = MutableLiveData<List<PicEntity>>()
    val favList: LiveData<List<PicEntity>> = _favList

    fun getFavouriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            _favList.postValue(dbRepository.getFavourites())
        }
    }

    fun toggleFavourite(picEntity: PicEntity) {
        viewModelScope.launch {
            dbRepository.toggleFavouritePic(
                picEntity.id,
                picEntity.isFavourite!!,
                Transformer.getTodaysDateInString()
            )
        }
    }

}