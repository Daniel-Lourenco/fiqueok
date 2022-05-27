package com.example.fiqueok.ui.agendamentolist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.fiqueok.R
import com.example.fiqueok.ui.agendamentolist.AgendamentoListAdapter
import com.example.fiqueok.data.db.AppDatabase
import com.example.fiqueok.data.db.dao.AtendimentosDAO
import com.example.fiqueok.extension.navigateWithAnimations
import com.example.fiqueok.repository.AgendamentoRepository
import com.example.fiqueok.repository.DatabaseDataSource
import kotlinx.android.synthetic.main.agendamento_list_fragment.*

class AgendamentoListFragment : Fragment(R.layout.agendamento_list_fragment) {

    private val viewModel: AgendamentoListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val atendimentosDAO: AtendimentosDAO =
                    AppDatabase.getInstance(requireContext()).atendimentosDAO

                val repository: AgendamentoRepository = DatabaseDataSource(atendimentosDAO)
                return AgendamentoListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListeners()

    }

    private fun observeViewModelEvents() {
        viewModel.allAgendamentosEvent.observe(viewLifecycleOwner) { allAgendamentos ->
            val agendamentoListAdapter = AgendamentoListAdapter(allAgendamentos).apply {
                onItemClick = { agendamento ->
                    val directions = AgendamentoListFragmentDirections
                        .actionAgendamentoListFragmentToAgendamentoFragment(agendamento)
                    findNavController().navigateWithAnimations(directions)
                }
            }

            with(recycler_agendamentos) {
                setHasFixedSize(true)
                adapter = agendamentoListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAgendamentos()
    }

    private fun configureViewListeners() {
        fabAddAgendamento.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_agendamentoListFragment_to_agendamentoFragment)
        }
    }

}