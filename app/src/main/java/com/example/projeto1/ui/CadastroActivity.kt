package com.example.projeto1.ui
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto1.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var imagemUri: Uri? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            try {
                contentResolver.takePersistableUriPermission(
                    it,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                imagemUri = it
                binding.imgPreview.setImageURI(it)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Novo Local"

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSelecionarImagem.setOnClickListener {
            getContent.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val categoria = binding.editCategoria.text.toString()
            val descricao = binding.editDescricao.text.toString()
            val telefone = binding.editTelefone.text.toString()

            if (nome.isNotEmpty() && categoria.isNotEmpty()) {
                val servico = com.example.projeto1.model.Servico(
                    nome = nome,
                    categoria = categoria,
                    descricao = descricao,
                    telefone = telefone,
                    imagem = com.example.projeto1.R.drawable.ic_launcher_background, // Placeholder standard
                    imagemUri = imagemUri?.toString()
                )

                com.example.projeto1.data.AppDatabase.getDatabase(this).servicoDao().insert(servico)
                finish()
            } else {
                binding.editNome.error = "Obrigatório"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}