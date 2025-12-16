package com.example.projeto1.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projeto1.data.AppDatabase
import com.example.projeto1.databinding.ActivityDetalheServicoBinding
import com.example.projeto1.model.Servico

class DetalheServicoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val servico = intent.getSerializableExtra("servico") as? Servico

        servico?.let {
            if (!it.imagemUri.isNullOrEmpty()) {
                binding.imgDetalhe.setImageURI(Uri.parse(it.imagemUri))
            } else {
                binding.imgDetalhe.setImageResource(it.imagem)
            }
            binding.txtNomeDetalhe.text = it.nome
            binding.txtCategoriaDetalhe.text = it.categoria
            binding.txtDescricaoDetalhe.text = it.descricao
        }

        binding.btnLigar.setOnClickListener {
            servico?.let {
                val telefone = it.telefone
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefone"))
                startActivity(intent)
            }
        }

        binding.btnExcluir.setOnClickListener {
            servico?.let {
                val db = AppDatabase.getDatabase(this)
                db.servicoDao().delete(it)
                finish()
            }
        }

    }
}
