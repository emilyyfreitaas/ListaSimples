package com.example.projeto01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etnovatarefa = findViewById<EditText>(R.id.etnovatarefa)
        val btadd = findViewById<Button>(R.id.btadd)
        val lvtarefas = findViewById<ListView>(R.id.lvtarefas)

        // onde se cria a lista de strings, inicialmente vazia
        val listaTarefas: ArrayList<String> = ArrayList()

        // para listas, é necessario um adapter
        // um componente add para layout de listas
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)

        // onde o adapter do listview recebe o adapter criado
        lvtarefas.adapter = adapter

        btadd.setOnClickListener {
            if (etnovatarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()
            } else {
                listaTarefas.add(etnovatarefa.text.toString())
                // é notificado ao adapter que a lista foi alterada
                // ao ser notificado, ele atualiza os novos elementos da lista na tela
                adapter.notifyDataSetChanged()
                etnovatarefa.setText("")
            }
        }

        // listener para efeito de clique longo em algum item da lista
        // se passa a posição de callback do item clicado
        lvtarefas.setOnItemLongClickListener { _, _, position, _ ->
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atenção!")
            alerta.setMessage("Quer mesmo excluir esse item?")
            alerta.setPositiveButton("Confirmar") {dialog, _ ->

                // caso o botao seja clicado, mover item da lista
                listaTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss() }
            alerta.setNegativeButton("Cancelar") {dialog, _ ->
                dialog.dismiss() }
            alerta.create().show()
            true
        }
    }
}