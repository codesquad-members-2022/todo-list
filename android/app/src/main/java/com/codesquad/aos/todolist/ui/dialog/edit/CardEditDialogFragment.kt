package com.codesquad.aos.todolist.ui.dialog.edit

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.databinding.FragmentCardDialogBinding
import com.codesquad.aos.todolist.databinding.FragmentCardDialogForEditBinding
import com.codesquad.aos.todolist.ui.TodoViewModel

class CardEditDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCardDialogForEditBinding
    private val viewModel: TodoViewModel by activityViewModels()

    private var order = -1
    private var cardId = -1
    private var section: String? = ""
    private var title: String? = ""
    private var content: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardDialogForEditBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val mArgs = arguments
        mArgs?.let {
            order = it.getInt("order")
            cardId = it.getInt("id")
            section = it.getString("section")
            title = it.getString("title")
            content = it.getString("content")
        }

        Log.d("AppTest", "${order}, ${cardId}, ${title}, ${content}, ${section}")

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

        binding.etEnterTitle.setText(title)
        binding.etEnterContents.setText(content)

        binding.btnEdit.setOnClickListener {
            if (binding.etEnterContents.text.isNotEmpty() && binding.etEnterTitle.text.isNotEmpty()) {
                viewModel.editCard(
                    cardId,
                    binding.etEnterContents.text.toString(),
                    order,
                    section!!,
                    binding.etEnterTitle.text.toString()
                )
                dismiss()
            } else if (binding.etEnterTitle.text.isNotEmpty()) {
                viewModel.editCard(
                    cardId,
                    binding.etEnterContents.text.toString(),
                    order,
                    section!!,
                    ""
                )
                dismiss()
            } else if (binding.etEnterContents.text.isNotEmpty()) {
                viewModel.editCard(
                    cardId,
                    "",
                    order,
                    section!!,
                    binding.etEnterTitle.text.toString()
                )
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