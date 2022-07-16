package alis.viruswala.phanmemkientiennew.privacy.viewmodel

import alis.viruswala.phanmemkientiennew.common.model.DataModelss
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import alis.viruswala.phanmemkientiennew.common.utils.Utilities.Companion.privacyDesc
import alis.viruswala.phanmemkientiennew.common.utils.Utilities.Companion.privacyTitle

class PrivacyViewModel : ViewModel(){

    private var privacyList = ArrayList<DataModelss>()
    private var privacyInfo = MutableLiveData<List<DataModelss>>()
    val trmNf : LiveData<List<DataModelss>>
        get() = privacyInfo
    private var privacyError = CoroutineExceptionHandler { _, _ ->
        privacyInfo.postValue(null)
    }

    fun termiFun(): MutableLiveData<List<DataModelss>> {
        viewModelScope.launch(privacyError + Dispatchers.IO) {
            for (n in privacyTitle.indices) {
                privacyList.add(DataModelss(privacyTitle[n], privacyDesc[n]))
            }
            privacyInfo.postValue(privacyList)
        }
        return privacyInfo
    }
}