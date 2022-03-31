package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.adapter.TarefaAdapter
import com.example.todo.databinding.FragmentListBinding


class ListFragment : Fragment() {


    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.listTarefa()

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        val adapter = TarefaAdapter()

        //Definir o Layout Manager da RecyclerView
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)

        //Passar o adapter criado para o recyclerTarefa
        binding.recyclerTarefa.adapter = adapter

        //Definir a lista para ter um tamanho fixo independente dos itens
        binding.recyclerTarefa.setHasFixedSize(true)

        //mainViewModel.listCategoria()

        //Navegação para o Fragment de Form
        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

      mainViewModel.myTarefaResponse.observe(viewLifecycleOwner)
    {
        response ->
        if (response != null) {
            adapter.setLista(response.body()!!)
        }
    }
      return binding.root
    }
}

