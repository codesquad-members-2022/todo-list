package com.codesquad.aos.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.codesquad.aos.todolist.databinding.FragmentCardDialogBinding

class TodoDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCardDialogBinding
    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btEnroll.setOnClickListener {
            if(binding.etEnterContents.text.isNotEmpty() && binding.etEnterTitle.text.isNotEmpty()){
                viewModel.addTodo(binding.etEnterTitle.text.toString(), binding.etEnterContents.text.toString())
                dismiss()
            }
        }
    }





}