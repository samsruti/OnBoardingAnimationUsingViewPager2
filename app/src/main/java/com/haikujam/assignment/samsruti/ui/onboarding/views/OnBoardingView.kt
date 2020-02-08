

package com.haikujam.assignment.samsruti.ui.onboarding.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.haikujam.assignment.samsruti.R

class OnBoardingView(layoutInflater: LayoutInflater, container: ViewGroup?) {

    val view: View = layoutInflater.inflate(R.layout.item_onboarding_content, container, false)

    private val messageTv: TextView

    init {
        messageTv = view.findViewById(R.id.message)
    }

    fun bind(onBoardingContent: OnBoardingContent) {

        val message = onBoardingContent.message
        messageTv.text = message
    }

}
