package com.example.fiqueok.ui.agendamento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fiqueok.R
import com.example.fiqueok.data.db.AppDatabase
import com.example.fiqueok.data.db.dao.AtendimentosDAO
import com.example.fiqueok.extension.hideKeyboard
import com.example.fiqueok.repository.AgendamentoRepository
import com.example.fiqueok.repository.DatabaseDataSource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.agendamento_fragment.*


class AgendamentoFragment : Fragment(R.layout.agendamento_fragment) {

    private var dataSelecionada: String ="0"
    private val viewModel: AgendamentoViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val atendimentosDAO: AtendimentosDAO =
                    AppDatabase.getInstance(requireContext()).atendimentosDAO

                val repository: AgendamentoRepository = DatabaseDataSource(atendimentosDAO)
                return AgendamentoViewModel(repository) as T
            }
        }
    }

    private val args: AgendamentoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.agendamento?.let { agendamento ->
            button_agendamento.text = getString(R.string.agendamento_button_update)
            input_especialidade.setText(agendamento.especialidade)
            input_data.text = agendamento.data//?.toLocalDate().toString()
            input_horario.setText(agendamento.horario)

            button_delete.visibility = View.VISIBLE
        }
        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.agendamentoStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is AgendamentoViewModel.AgendamentoState.Inserted,
                is AgendamentoViewModel.AgendamentoState.Updated,
                is AgendamentoViewModel.AgendamentoState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }

            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_especialidade.text?.clear()
        input_data.text = ""
        input_horario.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_data.setOnClickListener {
            DatePickerFragment {
                result -> input_data.text = result
            }
                .show(childFragmentManager,"datePicker")
        }


        button_agendamento.setOnClickListener {
            val especialidade = input_especialidade.text.toString()
            val data = input_data?.text.toString()
            val horario = input_horario.text.toString()

            viewModel.addOrUpdateAgendamento(especialidade, data, horario, args.agendamento?.id ?: 0)
        }

        button_delete.setOnClickListener{
            viewModel.removeAgendamento(args.agendamento?.id ?: 0)
        }
    }
}