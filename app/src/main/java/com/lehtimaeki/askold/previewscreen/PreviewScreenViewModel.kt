package com.lehtimaeki.askold.previewscreen

import androidx.lifecycle.ViewModel
import com.lehtimaeki.askold.ColorPalettes
import com.lehtimaeki.askold.iconset.IconSet
import com.lehtimaeki.askold.iconset.IconSetRepo
import com.lehtimaeki.askold.landingscreen.IconSetWrapper
import kotlinx.coroutines.flow.MutableStateFlow

class PreviewScreenViewModel : ViewModel() {

    var icons = MutableStateFlow<List<IconWrapper>>(emptyList())
    private val iconSetsList = ArrayList<IconWrapper>()

    fun loadIconSet(iconSetWrapper: IconSetWrapper?) {

        for (paidIconSet: IconSet in IconSetRepo.paidIconSets) {
            if (paidIconSet.id == iconSetWrapper?.iconSet?.id) {
                val iconsList = ArrayList<IconWrapper>()

                paidIconSet.icons.forEach { icon ->
                    iconsList.add(
                        IconWrapper(
                            icon,
                            ColorPalettes.getNextColorFromPalette(paidIconSet.useLightPalette)
                        )
                    )
                }

                iconSetsList.addAll(iconsList)
            }
        }
        icons.value = iconSetsList
    }
}

data class IconWrapper(val iconId: Int, val colorId: Int)