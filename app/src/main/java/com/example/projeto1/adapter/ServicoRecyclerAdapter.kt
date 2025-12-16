package com.example.projeto1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto1.databinding.ItemServicoBinding
import com.example.projeto1.model.Servico

class ServicoRecyclerAdapter(
    private var listaServicos: List<Servico>,
    private val onClick: (Servico) -> Unit
) : RecyclerView.Adapter<ServicoRecyclerAdapter.ServicoViewHolder>() {

    fun updateList(newList: List<Servico>) {
        listaServicos = newList
        notifyDataSetChanged()
    }

    inner class ServicoViewHolder(private val binding: ItemServicoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(servico: Servico) {
            if (!servico.imagemUri.isNullOrEmpty()) {
                binding.imgServico.setImageURI(android.net.Uri.parse(servico.imagemUri))
            } else {
                binding.imgServico.setImageResource(servico.imagem)
            }
            binding.txtNome.text = servico.nome
            binding.txtCategoria.text = servico.categoria
            binding.txtDescricao.text = servico.descricao

            itemView.setOnClickListener {
                onClick(servico)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val binding = ItemServicoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServicoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        holder.bind(listaServicos[position])
    }

    override fun getItemCount(): Int = listaServicos.size
}
