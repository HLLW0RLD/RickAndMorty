package com.example.testtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testtask.databinding.FragmentDetailsBinding
import com.example.testtask.model.character.Character
import com.example.testtask.model.factory.ViewModelFactory
import com.example.testtask.model.repository.RemoteRepository
import com.example.testtask.viewmodel.ListViewModel

class DetailsFragment : Fragment() {

    companion object{

        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle) : DetailsFragment{
            var details = DetailsFragment()
            details.arguments = bundle
            return details
        }
    }

    private val viewModel : ListViewModel by activityViewModels{ ViewModelFactory(RemoteRepository()) }


    private var _binding : FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var characterBundle: Character

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.characterData.observe(viewLifecycleOwner, { loadData(it) })

        arguments?.getParcelable<Character>(BUNDLE_EXTRA)?.let { viewModel.getCharacter(it) }

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root

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