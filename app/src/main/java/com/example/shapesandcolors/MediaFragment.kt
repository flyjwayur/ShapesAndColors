package com.example.shapesandcolors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_media_input.view.*

private const val ARG1 = "param1"

class MediaFragment : Fragment() {

    private lateinit var viewOfFragment: View
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            param1 = arguments!!.getInt(ARG1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOfFragment = inflater.inflate(R.layout.activity_media_input, container, false)

        viewOfFragment.btn_save.setOnClickListener { onSaveMedia(it) }
        viewOfFragment.btn_cancel.setOnClickListener { onCancelMedia(it) }
        return viewOfFragment
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int): MediaFragment {
            val args: Bundle = Bundle()
            args.putInt(ARG1, param1)
            val fragment = MediaFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun onSaveMedia(v: View?) {
        // code here
    }

    private fun onCancelMedia(v: View?) {
        // code here
        // imv_scrollview.visibility = View.VISIBLE
        activity?.onBackPressed()
    }
}