package com.example.todo.ui.toDo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todo.R
import com.example.todo.databinding.DialogUpdateCardBinding
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem
import com.example.todo.network.UpdateTodoBody

class UpdateToDoDialog(private val item: TodoItem) : DialogFragment() {

    lateinit var binding: DialogUpdateCardBinding
    private val viewModel: ToDoViewModel by activityViewModels()
    private var titleValidationFlag = true
    private var contentValidationFlag = true
    lateinit var btn: Button
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
        Log.d("testtt", (binding.btnDialogUpdate == null).toString())
        binding.btnDialogUpdate?.setOnClickListener {
            Log.d("testtt", "ss")
            val copy = item.copy()
            copy.content = binding.editCardContent.text.toString()
            copy.title = binding.editCardTitle.text.toString()
            viewModel.updateTodoItem(copy)
            Log.d("testtt", "ss")
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
        binding.btnDialogUpdate?.isEnabled = titleValidationFlag && contentValidationFlag

    }

}