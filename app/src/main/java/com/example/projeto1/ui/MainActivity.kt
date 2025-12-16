package com.example.projeto1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projeto1.R
import com.example.projeto1.adapter.ServicoRecyclerAdapter
import com.example.projeto1.data.AppDatabase
import com.example.projeto1.databinding.ActivityMainBinding
import com.example.projeto1.model.Servico

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ServicoRecyclerAdapter
    private lateinit var listaServicos: List<Servico>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val db = AppDatabase.getDatabase(this)
        listaServicos = db.servicoDao().getAll()
        adapter.updateList(listaServicos)
    }

    private fun setupViews() {
        val db = AppDatabase.getDatabase(this)
        if (db.servicoDao().getAll().isEmpty()) {
            val inicial = listOf(
                Servico(nome = "Polar", categoria = getString(R.string.categoria_food), descricao = "Distribuidora de Bebidas, Mini Mercado e Açougue", imagem = R.drawable.polar, telefone = "(16) 99600-3535"),
                Servico(nome = "Apadoka", categoria = getString(R.string.categoria_food), descricao = "Padaria, Confeitaria e Cafeteria", imagem = R.drawable.apadoka, telefone = "(16) 3332-8990"),
                Servico(nome = "ImperialGrill", categoria = getString(R.string.categoria_food), descricao = "Utensilio de Churrascaria", imagem = R.drawable.imperialgrill, telefone = "(16) 3014-1597"),
                Servico(nome = "Style", categoria = getString(R.string.categoria_beauty), descricao = "Cabeleireiro", imagem = R.drawable.style, telefone = "(16) 3322-5201"),
                Servico(nome = "Rede7", categoria = getString(R.string.categoria_others), descricao = "Posto de Gasolina", imagem = R.drawable.rede7, telefone = "(99) 9999-9999"),
                Servico(nome = "Solinos", categoria = getString(R.string.categoria_others), descricao = "Pet Shop", imagem = R.drawable.solinos, telefone = "(16) 99730-0730")
            )
            inicial.forEach { db.servicoDao().insert(it) }
        }
        
        listaServicos = db.servicoDao().getAll()

        binding.recyclerViewServicos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter = ServicoRecyclerAdapter(listaServicos) { servico ->
            val intent = Intent(this, DetalheServicoActivity::class.java)
            intent.putExtra("servico", servico)
            startActivity(intent)
        }
        binding.recyclerViewServicos.adapter = adapter

        setupSearch()
    }

    private fun setupSearch() {
        binding.editPesquisa.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString()
                val listaFiltrada = if (filtro.isEmpty()) {
                    listaServicos
                } else {
                    listaServicos.filter { it.nome.contains(filtro, ignoreCase = true) }
                }
                adapter.updateList(listaFiltrada)
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
