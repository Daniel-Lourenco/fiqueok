package com.example.fiqueok.ui.agendamentolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fiqueok.R
import com.example.fiqueok.data.db.entity.AgendamentoEntity
import kotlinx.android.synthetic.main.agendamento_item.view.*

class AgendamentoListAdapter(
    private val agendamentos: List<AgendamentoEntity>
) : RecyclerView.Adapter<AgendamentoListAdapter.AgendamentoListViewHolder>() {

    var onItemClick: ((entity: AgendamentoEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.agendamento_item, parent, false)

        return AgendamentoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgendamentoListViewHolder, position: Int) {
        holder.bindView(agendamentos[position])
    }

    override fun getItemCount(): Int = agendamentos.size

    inner class AgendamentoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewAgendamentoEspecialidade: TextView = itemView.text_agendamento_especialidade
        private val textViewAgendamentoData: TextView = itemView.text_agendamento_data
        private val textViewAgendamentoHorario: TextView = itemView.text_agendamento_horario

        fun bindView(agendamento: AgendamentoEntity) {
            textViewAgendamentoEspecialidade.text = agendamento.especialidade
            textViewAgendamentoData.text = agendamento.data?.toLocalDate().toString()
            textViewAgendamentoHorario.text = agendamento.horario

            itemView.setOnClickListener{
                onItemClick?.invoke(agendamento)
            }
        }
    }
}
