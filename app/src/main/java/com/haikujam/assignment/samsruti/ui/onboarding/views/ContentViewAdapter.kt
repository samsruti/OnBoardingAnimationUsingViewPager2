package com.haikujam.assignment.samsruti.ui.onboarding.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(OnBoardingView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(OnBoardingContent.MAPPED_VALUES[position])
    }

    override fun getItemCount(): Int {
        return OnBoardingContent.MAPPED_VALUES.size
    }


}

class CardViewHolder internal constructor(private val onBoardingView: OnBoardingView) :
    RecyclerView.ViewHolder(onBoardingView.view) {
    internal fun bind(onBoardingContent: OnBoardingContent) {
        onBoardingView.bind(onBoardingContent)
    }
}
