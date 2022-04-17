package com.example.todolist.tasks

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.ServiceLocator
import com.example.todolist.ViewModelFactory
import com.example.todolist.databinding.DialogFragBinding

enum class DialogAction {
    ADD,
    UPDATE
}

class TaskDialogFragment : DialogFragment() {
    private var _binding: DialogFragBinding? = null
    private val binding
        get() = _binding!!

    private val repository = ServiceLocator.provideRepository()
    private lateinit var viewModel: TaskDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(TaskDialogViewModel::class.java)
        return inflater.inflate(R.layout.dialog_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewmodel = viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val action = requireArguments().let {
            it["action"]
        } as DialogAction

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_frag, null)
            _binding = DataBindingUtil.bind(view)

            setDialogTitleAndButtonText(action)
            setupListeners()
            builder.setView(view)

            val dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setupListeners() {
        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnOk.setOnClickListener {
            val bundle = bundleOf(
                "title" to binding.editTaskTitle.text.toString(),
                "body" to binding.editTaskBody.text.toString()
            )
            parentFragmentManager.setFragmentResult("addTask", bundle)
            dismiss()
        }

        binding.editTaskTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.checkTitle.value = s?.length != 0
            }
        })

        binding.editTaskBody.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.checkBody.value = s?.length != 0
            }
        })
    }

    private fun setDialogTitleAndButtonText(action: DialogAction) {
        when (action) {
            DialogAction.ADD -> {
                binding.textDialogTitle.text = getString(R.string.dialog_add_title)
                binding.btnOk.text = getString(R.string.dialog_add_button)
            }
            DialogAction.UPDATE -> {
                binding.textDialogTitle.text = getString(R.string.dialog_update_title)
                binding.btnOk.text = getString(R.string.dialog_update_button)
            }
        }
    }
}