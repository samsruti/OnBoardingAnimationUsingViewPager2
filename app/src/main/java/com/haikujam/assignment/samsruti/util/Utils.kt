package com.haikujam.assignment.samsruti.util

import android.view.View

fun getViewCenterCoordinates(onBoardingCircleView: View): Pair<Float, Float> {
    val parentX = onBoardingCircleView.x + onBoardingCircleView.width/2
    val parentY = onBoardingCircleView.y + onBoardingCircleView.height/2

    return Pair(parentX, parentY)
}