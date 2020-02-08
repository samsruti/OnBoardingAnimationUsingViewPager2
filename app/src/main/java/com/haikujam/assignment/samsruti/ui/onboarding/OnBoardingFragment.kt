package com.haikujam.assignment.samsruti.ui.onboarding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.haikujam.assignment.samsruti.R
import com.haikujam.assignment.samsruti.databinding.OnBoardingFragmentBinding
import com.haikujam.assignment.samsruti.ui.onboarding.views.CardViewAdapter
import com.haikujam.assignment.samsruti.ui.onboarding.views.OnBoardingContent
import com.haikujam.assignment.samsruti.util.getViewCenterCoordinates
import kotlinx.android.synthetic.main.on_boarding_fragment.*


class OnBoardingFragment : Fragment() {

    private val onBoardingFragmentArgs: OnBoardingFragmentArgs by navArgs()

    private lateinit var viewModel: OnBoardingViewModel
    private lateinit var binding: OnBoardingFragmentBinding

    private var animationDuration: Long = 750L


    private lateinit var colorList: Array<ColorDrawable>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        Log.v("OnBoarding", "onCreate called")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = OnBoardingFragmentBinding.inflate(inflater,container,false)

        Log.v("Parameter - Language", "${onBoardingFragmentArgs.languageId}")

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OnBoardingViewModel::class.java)

        Log.v("OnBoarding", "onActivityCreated called")

//        TODO: Refactoring for ViewModel

        colorList = arrayOf(
            ColorDrawable(ContextCompat.getColor(binding.root.context, R.color.defaultGray)),
            ColorDrawable(ContextCompat.getColor(binding.root.context,R.color.defaultGray))
        )

        binding.viewPager.apply {
            isUserInputEnabled = false
            adapter = CardViewAdapter()
        }



        TabLayoutMediator(binding.tabsWithDots, binding.viewPager ){
                tab, _ ->
            tab.text = ""
        }.attach()


        binding.button.setOnClickListener {
            val size = binding.viewPager.adapter!!.itemCount
            val currentItem = binding.viewPager.currentItem

            when (val nextItem = currentItem + 1) {
                size -1 -> {
                    updateButtonText()
                    binding.viewPager.setCurrentItem(nextItem, true)
                    animateViews(currentItem)
                }
                size -> {
                    Toast.makeText(binding.root.context,"Done redirecting", Toast.LENGTH_SHORT).show()
                    redirectToNextPage()
                    binding.viewPager.setCurrentItem(0, false)
                }
                else -> {
                    binding.viewPager.setCurrentItem(nextItem, true)
                    animateViews(currentItem)
                }
            }


        }

    }


    private fun animateViews(currentItem:Int) {

        val (parentX, parentY) = getViewCenterCoordinates(binding.onBoardingCircleView)
        val (avatarId, emptyViewId) = OnBoardingContent.MAPPED_AVATAR_WTH_VIEWS[currentItem]

        val imageView: ImageView = binding.root.findViewById(avatarId)
        val view: View = binding.root.findViewById(emptyViewId)

        with(imageView.animate()){
            translationY(parentY - imageView.height/2 - imageView.y )
            translationX(parentX - imageView.width/2 - imageView.x )
            alpha(0f)
            duration = animationDuration
            setListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    imageView.visibility = View.GONE
                    animateViewBackgroundColor(view,currentItem)
                }
            })
        }
    }

    private fun animateViewBackgroundColor(view: View,currentItem: Int) {

        view.setBackgroundColor(Color.RED)

        val colorsMap = listOf(
            R.color.darkRed,
            R.color.red,
            R.color.orange
        )
        val drawable = ColorDrawable(
            ContextCompat.getColor(
                view.context,
                colorsMap[currentItem]
            )
        )
        colorList[1] = drawable

        val trans = TransitionDrawable(colorList)
        view.background = trans
        trans.startTransition(500)
    }

    private fun redirectToNextPage() {

        val extras = FragmentNavigatorExtras(onBoarding_circle_view to "cardViewAnimation")
        this.findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToJammingFragment(), extras)
    }

    private fun updateButtonText() {
        button.text = getString(R.string.proceed_button)
    }




}
