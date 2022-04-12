package com.example.todo.ui.toDo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todo.databinding.DialogUpdateCardBinding
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem

class UpdateToDoDialog(private val item: TodoItem) : DialogFragment() {

    lateinit var binding: DialogUpdateCardBinding
    private val viewModel : ToDoViewModel by activityViewModels()
    private var titleValidationFlag = true
    private var contentValidationFlag = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUpdateCardBinding.inflate(inflater, container, false)
        binding.editCardContent.setText(item.content)
        binding.editCardTitle.setText(item.title)
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnRegister.setOnClickListener {
            item.content= binding.editCardContent.text.toString()
            item.title= binding.editCardTitle.text.toString()
            when (item.type) {
                ProgressType.TO_DO ->  viewModel.updateTodoItem(item)
                ProgressType.IN_PROGRESS -> viewModel.updateInProgressItem(item)
                else -> viewModel.updateDoneItem(item)
            }
            dismiss()
        }

        binding.btnCancle.setOnClickListener { dismiss() }
        binding.editCardTitle.addTextChangedListener(titleChangeListener)
        binding.editCardContent.addTextChangedListener(contentChangeListener)
    }

    private val titleChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(titleInput: Editable?) {
            if (titleInput != null) {
                titleValidationFlag = when {
                    (titleInput.isEmpty()) -> false
                    else -> true
                }
            }
            validationFlagCheck()
        }
    }

    private val contentChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(contentInput: Editable?) {
            if (contentInput != null) {
                contentValidationFlag = when {
                    (contentInput.isEmpty()) -> false
                    else -> true
                }
            }
            validationFlagCheck()
        }
    }

    private fun validationFlagCheck() {
        binding.btnRegister.isEnabled = titleValidationFlag && contentValidationFlag

    }

}