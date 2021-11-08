package com.example.testtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testtask.databinding.DetailsBinding
import com.example.testtask.model.character.Character
import com.example.testtask.viewmodel.DetailsViewModel
import com.example.testtask.viewmodel.ListViewModel
import retrofit2.Response

class DetailsFragment : Fragment() {

    companion object{

        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle) : DetailsFragment{
            var details = DetailsFragment()
            details.arguments = bundle
            return details
        }
    }

    private val viewModel : DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel :: class.java)
    }

    private var _binding : DetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var characterBundle: Character

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

        _binding = DetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getCharFromRemote(54)

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

        characterBundle = arguments?.getParcelable<Character>(BUNDLE_EXTRA) ?: Character()

    }

    private fun loadData(response: Character) {

                binding.details.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE

                binding.apply {
                    name.text = response.name
                    species.text = response.species
                    status.text = response.status
                    gender.text = response.gender
                    origin.text = response.origin.name
                    location.text = response.location.name
                    type.text = response.type
                    episodes.text = response.episode.size.toString()

                    Glide
                        .with(root)
                        .load(response.image)
                        .into(detailsIcon)
                }
            }
}