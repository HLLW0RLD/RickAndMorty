package com.example.testtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.R
import com.example.testtask.databinding.ListBinding
import com.example.testtask.model.State
import com.example.testtask.model.character.Character
import com.example.testtask.viewmodel.ListViewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel : ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel :: class.java)
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }

    private var _binding: ListBinding? = null

    private val binding get() = _binding!!

    val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        viewModel.getLiveData().observe(viewLifecycleOwner, {loadData(it)})

        binding.recyclerview.adapter = adapter
    }

    private fun loadData(state: State?) {
        when(state){
        is State.Success ->{
            binding.list.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        }

        is State.Loading ->{
            binding.list.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        }
        }
    }

    private val characterClickListener = object : OnCharacterClickListener{
        override fun onCharacterClick(character: Character) {
            val manager = parentFragmentManager
            val bundle = Bundle()
            //bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, character)
            manager
                .beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack(DetailsFragment.BUNDLE_EXTRA)
                .commit()
        }
    }

}
