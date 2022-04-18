package com.example.todolist.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todolist.R
import com.example.todolist.data.NewCard
import com.example.todolist.databinding.DialogFragmentBinding

class CreateCardDialogFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentBinding
    private val viewModel: CardViewModel by activityViewModels()
    private var title = ""
    private var content = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.background_white)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAddTitleChangedListener()

        setAddContentChangedListener()

        setCancelClickListener()

        setAddCardListener()

    }

    private fun setAddContentChangedListener() {
        binding.tvEditContent.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                content = s.toString()
                binding.btnDialogRegister.isEnabled = title.isNotBlank() && content.isNotBlank()
            }

        })
    }

    private fun setAddCardListener() {
        binding.btnDialogRegister.setOnClickListener {
            kotlin.runCatching {
                val newCard = this.tag?.let { status -> NewCard(title, content, status) }
                    ?: throw IllegalArgumentException("tag id null")
                viewModel.addCard(newCard)
                this.dismiss()
            }.onFailure {
                Toast.makeText(requireContext(), "입력 값이 정확하지 않습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAddTitleChangedListener() {
        binding.tvEditTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                title = s.toString()
                binding.btnDialogRegister.isEnabled = title.isNotBlank() && content.isNotBlank()
            }
        })
    }

    private fun setCancelClickListener() {
        binding.btnDialogCancel.setOnClickListener {
            dialog?.cancel()
        }
    }



}
