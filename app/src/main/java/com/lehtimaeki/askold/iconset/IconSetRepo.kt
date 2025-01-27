package com.lehtimaeki.askold.iconset

import com.lehtimaeki.askold.utils.GameTileResources
import java.lang.NullPointerException
import kotlin.random.Random


object IconSetRepo {

    val allIconSets = mutableListOf<IconSet>()
    val paidIconSets = mutableListOf<IconSet>()
    private val allIconSetsMap = mutableMapOf<Int, IconSet>()
    private val random = Random.Default

    // Pairs of icon drawable and if it should be tinted
    private val allUnlockedIcons by lazy {
        val ret = mutableListOf<Pair<Int, Boolean>>()
        allIconSets.filter { it.isUnlocked }.forEach { iconSet ->
            iconSet.icons.forEach { icon ->
                ret.add(Pair(icon, iconSet.tintForContrast))
            }
        }
        ret.toList()
    }


    init {

        allIconSets.add(
            IconSet(
                id = 1,
                name = "Numbers",
                isUnlocked = true,
                GameTileResources.NUMBERS,
                tintForContrast = false,
                useLightPalette = true,
                excludeFirstAsset = true
            )
        )
        allIconSets.add(
            IconSet(
                id = 2,
                name = "Shapes",
                isUnlocked = true,
                GameTileResources.SHAPES_ICONS,
                tintForContrast = false,
                excludeFirstAsset = true
            )
        )
//        allIconSets.add(
//            IconSet(
//                id = 2,
//                name = "Alphabet",
//                isUnlocked = true,
//                GameTileResources.LETTERS,
//                tintForContrast = true
//            )
//        )
        paidIconSets.add(
            IconSet(
                id = 3,
                name = "Woodlands",
                isUnlocked = false,
                GameTileResources.WOODLANDS_ICONS,
                tintForContrast = false
            )
        )
        paidIconSets.add(
            IconSet(
                id = 4,
                name = "African animals",
                isUnlocked = false,
                GameTileResources.AFRICAN_ANIMAL_ICONS,
                tintForContrast = false
            )
        )

        allIconSets.forEach {
            allIconSetsMap[it.id] = it
        }
    }


    /**
     *
     * @return pair of icon drawable and if it should be tinted
     */
    fun getRandomIcon(): Pair<Int, Boolean> {
        return allUnlockedIcons[random.nextInt(allUnlockedIcons.size)]
    }

    fun getRandomIcon(iconSetId: Int): Triple<Int, Boolean, Boolean> {
        val iconSet = allIconSetsMap[iconSetId]
            ?: throw NullPointerException("Did not find an icon set for id $iconSetId")

        return Triple(
            if (iconSet.excludeFirstAsset) iconSet.icons.get(random.nextInt(iconSet.icons.size - 1) + 1)
            else iconSet.icons.get(
                random.nextInt(iconSet.icons.size)
            ),
            iconSet.tintForContrast,
            iconSet.useLightPalette
        )
    }


}