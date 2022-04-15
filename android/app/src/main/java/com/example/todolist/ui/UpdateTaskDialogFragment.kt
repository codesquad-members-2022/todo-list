package com.example.todolist.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todolist.databinding.DialogUpdateCardBinding
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.TaskDetailResponse

class UpdateTaskDialogFragment(private val task: TaskDetailResponse) : DialogFragment() {
    private lateinit var binding: DialogUpdateCardBinding
    private val viewModel: TaskRemoteViewModel by activityViewModels()
    private var titleFlag = false
    private var contentsFlag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogUpdateCardBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 다이얼로그의 곡선 주변에 배경색을 맞춰주는 코드
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false) // 다이얼로그 외부의 영역 터치 시 취소 불가능

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCancel.setOnClickListener { dismiss() }

        binding.etTitle.addTextChangedListener(titleListener)
        binding.etContents.addTextChangedListener(contentsListener)
        binding.etTitle.setText(task.title)
        binding.etContents.setText(task.contents)

        binding.btnUpdate.setOnClickListener {
            val modifyTaskRequest = ModifyTaskRequest(
                task.id,
                binding.etTitle.text.toString(),
                binding.etContents.text.toString(),
                "Android",
                task.status.toString()
            )
            viewModel.modifyTask(modifyTaskRequest)
            dismiss()
        }
    }

    private val titleListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                titleFlag = when {
                    s.isEmpty() -> false
                    else -> true
                }
            }
            flagCheck()
        }
    }

    private val contentsListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                contentsFlag = when {
                    s.isEmpty() -> false
                    else -> true
                }
            }
            flagCheck()
        }
    }

    fun flagCheck() {
        binding.btnUpdate.isEnabled = titleFlag && contentsFlag
    }
}