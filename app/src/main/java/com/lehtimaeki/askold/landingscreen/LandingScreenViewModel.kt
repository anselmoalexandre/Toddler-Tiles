package com.lehtimaeki.askold.landingscreen

import androidx.lifecycle.ViewModel
import com.lehtimaeki.askold.iconset.IconSetRepo
import kotlinx.coroutines.flow.MutableStateFlow

class LandingScreenViewModel : ViewModel() {
    var iconSets = MutableStateFlow<List<IconSetWrapper>>(emptyList())
    private val iconSetsList = ArrayList<IconSetWrapper>()

    init {

        iconSetsList.add(IconSetWrapper(0, null, "Begin Learning"))
        IconSetRepo.allIconSets.filter { it.isUnlocked }.forEach { iconSet ->
            iconSetsList.add(IconSetWrapper(iconSet.id, iconSet, null))
        }

        iconSetsList.add(IconSetWrapper(Int.MAX_VALUE, null, "Buy more fun sets"))
        IconSetRepo.paidIconSets.filter { !it.isUnlocked }.forEach { iconSet ->
            iconSetsList.add(IconSetWrapper(iconSet.id, iconSet, null))
        }

        iconSets.value = iconSetsList
    }

    fun addPaidIconSets(paidIconSets: ArrayList<IconSetWrapper>) {
        paidIconSets.addAll(iconSets.value)
        iconSets.value = paidIconSets
    }

    fun unlockPaidIconSet(paidIconSetId: Int) {
        val iconList = iconSets.value
        iconList[paidIconSetId].iconSet?.isUnlocked = true
        iconSets.value = iconList
    }
}