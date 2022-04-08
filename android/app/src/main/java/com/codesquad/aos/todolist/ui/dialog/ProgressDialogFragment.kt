package com.codesquad.aos.todolist.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.databinding.FragmentCardDialogBinding
import com.codesquad.aos.todolist.ui.TodoViewModel

class ProgressDialogFragment : DialogFragment() {

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

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var windowManager: WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if(Build.VERSION.SDK_INT < 30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val width = (size.x * 400 / 1194).toInt()
            binding.cvDialog.layoutParams.width = width
        }
        else{
            val rect = windowManager.currentWindowMetrics.bounds
            val width = rect.width() * 400 / 1194
            binding.cvDialog.layoutParams.width = width
        }

        binding.btnEnroll.setOnClickListener {
            if (binding.etEnterContents.text.isNotEmpty() && binding.etEnterTitle.text.isNotEmpty()) {
                viewModel.addProgress(
                    binding.etEnterTitle.text.toString(),
                    binding.etEnterContents.text.toString()
                )
                dismiss()
            } else if (binding.etEnterTitle.text.isNotEmpty()) {
                viewModel.addProgress(binding.etEnterTitle.text.toString(), "")
                dismiss()
            } else if (binding.etEnterContents.text.isNotEmpty()) {
                viewModel.addProgress(getString(R.string.new_todo), binding.etEnterContents.text.toString())
                dismiss()
            } else {
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}