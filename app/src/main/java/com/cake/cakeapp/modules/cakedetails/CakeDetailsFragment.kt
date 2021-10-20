package com.cake.cakeapp.modules.cakedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cake.cakeapp.R
import com.cake.cakeapp.modules.cakelist.Cake
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cake_list_item.*
import kotlinx.android.synthetic.main.cake_list_item.cakeImageView
import kotlinx.android.synthetic.main.cake_list_item.description
import kotlinx.android.synthetic.main.cake_list_item.name
import kotlinx.android.synthetic.main.cake_list_item.view.*
import kotlinx.android.synthetic.main.cake_details_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE = "title"
private const val ARG_DESC = "desc"
private const val ARG_IMAGE = "image"

/**
 * [CakeDetailsFragment.newInstance] factory method
 * creates a instance of this fragment
 */
class CakeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var title: String? = null
    private var desc: String? = null
    private var image: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            desc = it.getString(ARG_DESC)
            image = it.getString(ARG_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cake_details_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name.text = title
        description.text = image

        Picasso.get()
            .load(desc)
            .placeholder(R.drawable.ic_default_cake_24)
            .error(R.drawable.ic_default_cake_24)
            .into(cakeImageView)

    }

    companion object {

        val TAG = CakeDetailsFragment::class.java.simpleName

        /**
         * This method to creates a new instance of this fragment
         * using the following parameters:
         *
         * @param title
         * @param desc
         * @param image
         *
         * @return A new instance of fragment CakeDetailsFragment.
         */
        @JvmStatic
        fun newInstance(cake: Cake) =
            CakeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, cake.title)
                    putString(ARG_DESC, cake.image)
                    putString(ARG_IMAGE, cake.desc)
                }
            }
    }
}