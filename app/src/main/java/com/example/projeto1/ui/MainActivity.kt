package com.example.projeto1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projeto1.R
import com.example.projeto1.adapter.ServicoAdapter
import com.example.projeto1.databinding.ActivityMainBinding
import com.example.projeto1.model.Servico

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ServicoAdapter
    private lateinit var listaServicos: List<Servico>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        listaServicos = listOf(
            Servico("Polar", getString(R.string.categoria_food), "Distribuidora de Bebidas, Mini Mercado e Açougue", R.drawable.polar, "(16) 99600-3535"),
            Servico("Apadoka", getString(R.string.categoria_food), "Padaria, Confeitaria e Cafeteria", R.drawable.apadoka, "(16) 3332-8990"),
            Servico("ImperialGrill", getString(R.string.categoria_food), "Utensilio de Churrascaria", R.drawable.imperialgrill, "(16) 3014-1597"),
            Servico("Style", getString(R.string.categoria_beauty), "Cabeleireiro", R.drawable.style, "(16) 3322-5201"),
            Servico("Rede7", getString(R.string.categoria_others), "Posto de Gasolina", R.drawable.rede7, "(99) 9999-9999"),
            Servico("Solinos", getString(R.string.categoria_others), "Pet Shop", R.drawable.solinos, "(16) 99730-0730")
        )

        adapter = ServicoAdapter(this, listaServicos)
        binding.listViewServicos.adapter = adapter
    }

    private fun setupListeners() {
        binding.listViewServicos.setOnItemClickListener { _, _, position, _ ->
            val servicoSelecionado = listaServicos[position]
            val intent = Intent(this, DetalheServicoActivity::class.java)
            intent.putExtra("servico", servicoSelecionado)
            startActivity(intent)
        }
    }
}
