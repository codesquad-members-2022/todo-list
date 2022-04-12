package com.example.todo.ui.toDo


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todo.databinding.DialogCardBinding
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem


class ToDoDialog(private val progressType: ProgressType) : DialogFragment() {

    lateinit var binding: DialogCardBinding
    private val viewModel: ToDoViewModel by activityViewModels()
    private var titleValidationFlag = false
    private var contentValidationFlag = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCardBinding.inflate(inflater, container, false)
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnRegister.setOnClickListener {
            when (progressType) {
                ProgressType.TO_DO -> {
                    val newToDoItem = TodoItem(
                        "test",
                        binding.editCardTitle.text.toString(),
                        binding.editCardContent.text.toString(),
                        ProgressType.TO_DO
                    )
                    viewModel.addTodoItem(newToDoItem)
                }

                ProgressType.IN_PROGRESS -> {
                    val newInProgressItem = TodoItem(
                        "test",
                        binding.editCardTitle.text.toString(),
                        binding.editCardContent.text.toString(),
                        ProgressType.IN_PROGRESS
                    )
                    viewModel.addInProgressItem(newInProgressItem)
                }
                else -> {
                    val newDoneItem = TodoItem(
                        "test",
                        binding.editCardTitle.text.toString(),
                        binding.editCardContent.text.toString(),
                        ProgressType.DONE
                    )

                    viewModel.addDoneItem(newDoneItem)
                }
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

