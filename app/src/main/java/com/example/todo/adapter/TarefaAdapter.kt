package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MainViewModel
import com.example.todo.R
import com.example.todo.model.Tarefa

class TarefaAdapter (
    private val taskItemClickListener: TaskItemClickListener,
    private val mainViewModel: MainViewModel
        ): RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefas = emptyList<Tarefa>()

    class TarefaViewHolder (view: View) : RecyclerView.ViewHolder(view){

        val textTarefa = view.findViewById<TextView>(R.id.textTarefaCard)
        val textDescricao = view.findViewById<TextView>(R.id.textDescricaoCard)
        val textResponsavel = view.findViewById<TextView>(R.id.TextRespoCard)
        val textData = view.findViewById<TextView>(R.id.textDataCard)
        val switchCard = view.findViewById<Switch>(R.id.switAndamentoCard)
        val textCategoria = view.findViewById<TextView>(R.id.textCategoriaCard)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder  {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_tarefas, parent, false)

        return TarefaViewHolder(layout)

    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefas[position]

        holder.textTarefa.text = tarefa.nome
        holder.textDescricao.text = tarefa.descricao
        holder.textResponsavel.text = tarefa.responsavel
        holder.textData.text = tarefa.data
        holder.switchCard.isChecked = tarefa.status
        holder.textCategoria.text = tarefa.categoria.descricao

        holder.itemView.setOnClickListener{
            taskItemClickListener.onTaskClicked(tarefa)
        }
        holder.switchCard
            .setOnCheckedChangeListener{ compoundButton, ativo ->
                tarefa.status = ativo
                mainViewModel.updateTarefa(tarefa)
            }
    }

    override fun getItemCount(): Int{
        return listTarefas.size

    }
    fun setLista(lista: List<Tarefa>){
        listTarefas = lista
        notifyDataSetChanged()
    }

}
