package com.astropics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astropics.db.entity.PicEntity
import com.astropics.di.DBRepository
import com.astropics.di.NetworkRepository
import com.astropics.di.Transformer
import com.astropics.di.Transformer.getTodaysDateInString
import com.astropics.model.PicResponse
import com.astropics.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DBRepository
) : ViewModel() {

    private val _pic = MutableLiveData<DataHandler<PicEntity>>()
    val pic: LiveData<DataHandler<PicEntity>> = _pic

    fun getPicByDate() {
        viewModelScope.launch {
            val picEntity = dbRepository.getPicByDate(getTodaysDateInString())
            if (picEntity == null) {
                _pic.postValue(DataHandler.LOADING())
                val response = networkRepository.getPicByDate()
                _pic.postValue(handleResponse(response))
            } else {
                _pic.postValue(DataHandler.SUCCESS(picEntity))
            }
        }
    }

    private fun handleResponse(response: Response<PicResponse>): DataHandler<PicEntity> {
        if (response.isSuccessful) {
            response.body()?.let {
                val picEntity = Transformer.convertNetworkResponseToDbEntity(it)
                insertPic(picEntity)
                return DataHandler.SUCCESS(picEntity)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }

    private fun insertPic(picEntity: PicEntity) {
        viewModelScope.launch {
            dbRepository.insertPic(picEntity)
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            val isFav = if (_pic.value!!.data!!.isFavourite!! == 0) 1 else 0
            dbRepository.toggleFavouritePic(pic.value!!.data!!.id, isFav, getTodaysDateInString())
            val picEntity = dbRepository.getPicByDate(getTodaysDateInString())
            picEntity?.let { _pic.postValue(DataHandler.SUCCESS(picEntity)) }
        }
    }
}