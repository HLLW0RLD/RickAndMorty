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

class DetailsFragment : Fragment() {

    companion object{

        const val BUNDLE_EXTRA = "BUNDLE_EXTRA"

        fun newInstance(bundle: Bundle) : DetailsFragment{
            var details = DetailsFragment()
            details.arguments = bundle
            return details
        }
    }

    private val viewModel : ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel :: class.java)
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

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

        viewModel.getCharFromRemote(characterBundle)

        characterBundle = arguments?.getParcelable<Character>(BUNDLE_EXTRA) ?: Character()

    }

    private fun loadData(state: State?) {
        when(state){

            is State.Success ->{
                binding.details.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE

                binding.apply {
                    name.text = state.characterData[0].name
                    species.text = state.characterData[0].species
                    status.text = state.characterData[0].status
                    gender.text = state.characterData[0].gender
                    origin.text = state.characterData[0].origin.name
                    location.text = state.characterData[0].location.name
                    type.text = state.characterData[0].type
                    episodes.text = state.characterData[0].episode.size.toString()

                    Glide
                        .with(root)
                        .load(state.characterData[0].image)
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