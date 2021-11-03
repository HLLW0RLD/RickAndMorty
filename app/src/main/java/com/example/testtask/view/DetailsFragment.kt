package com.example.testtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.testtask.databinding.DetailsBinding
import com.example.testtask.model.State
import com.example.testtask.model.character.Character
import com.example.testtask.viewmodel.ListViewModel

class Details : Fragment() {

    companion object{

        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle) : Details{
            var details = Details()
            details.arguments = bundle
            return details
        }
    }

    private val viewModel : ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel :: class.java)
    }

    private var _binding : DetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

        arguments?.getParcelable<Character>(BUNDLE_EXTRA)?.let { viewModel.getCharFromRemote(it) }

        _binding = DetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

    }

    private fun loadData(state: State?) {
        when(state){

            is State.Success ->{
                binding.details.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE

                binding.apply {
                    name.text = state.characterData.name
                    species.text = state.characterData.species
                    status.text = state.characterData.status
                    gender.text = state.characterData.gender
                    origin.text = state.characterData.origin.name
                    location.text = state.characterData.location.name
                    type.text = state.characterData.type
                    episodes.text = state.characterData.episode.size.toString()

                    Glide
                        .with(root)
                        .load(state.characterData.image)
                        .into(detailsIcon)
                }
            }

            is State.Loading ->{
                binding.details.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
            }

        }
    }
}