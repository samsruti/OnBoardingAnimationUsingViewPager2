
package com.haikujam.assignment.samsruti.ui.onboarding.views

import com.haikujam.assignment.samsruti.R

class OnBoardingContent private constructor(private val value: String){

    val message: String
        get() = value

//    val viewsMap:

    companion object {

        private val CONTENT_MESSAGES = setOf(
            "People around the world come together to write poems",
            "One person sets a topic and writes the first line",
            "One person sets a topic and writes the first line2",
            "Others will add to it and that's how you JAM!")

        private val AVATAR_LISTS = mutableListOf(
            R.id.thumbnail_top,
            R.id.thumbnail_left,
            R.id.thumbnail_right
        )

        private val RECTANGULAR_EMPTY_VIEWS = mutableListOf(
            R.id.view1,
            R.id.view2,
            R.id.view3
        )

        val MAPPED_AVATAR_WTH_VIEWS = AVATAR_LISTS.zip(RECTANGULAR_EMPTY_VIEWS)

        val MAPPED_VALUES = CONTENT_MESSAGES.map { value -> OnBoardingContent(value) }

    }

}
