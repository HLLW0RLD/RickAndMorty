package com.example.testtask.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.databinding.FragmentListBinding
import com.example.testtask.model.character.Character
import com.example.testtask.model.factory.ViewModelFactory
import com.example.testtask.model.repository.RemoteRepository
import com.example.testtask.viewmodel.ListViewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel : ListViewModel by activityViewModels{ ViewModelFactory(RemoteRepository()) }

    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        viewModel.listCharactersData.observe(viewLifecycleOwner, {adapter.setData(it)})

        binding.recyclerview.adapter = adapter

        adapter.setCharacterListener(characterClickListener)

    }

    private val characterClickListener = object : OnCharacterClickListener{
        override fun onCharacterClick(character: Character) {
            val manager = parentFragmentManager
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, character)
            manager
                .beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack(DetailsFragment.BUNDLE_EXTRA)
                .commit()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.getCharacters(1)
    }
}
