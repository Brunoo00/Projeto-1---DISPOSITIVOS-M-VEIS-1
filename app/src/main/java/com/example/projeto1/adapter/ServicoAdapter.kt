package com.example.projeto1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.projeto1.databinding.ItemServicoBinding
import com.example.projeto1.model.Servico

class ServicoAdapter(
    private val context: Context,
    private val listaServicos: List<Servico>
) : BaseAdapter() {

    override fun getCount(): Int = listaServicos.size

    override fun getItem(position: Int): Any = listaServicos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemServicoBinding
        val view: View

        if (convertView == null) {
            binding = ItemServicoBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
            view = binding.root
        } else {
            binding = convertView.tag as ItemServicoBinding
            view = convertView
        }

        val servico = listaServicos[position]
        binding.imgServico.setImageResource(servico.imagem)
        binding.txtNome.text = servico.nome
        binding.txtCategoria.text = servico.categoria
        binding.txtDescricao.text = servico.descricao

        return view
    }
}
