package com.xfs.flashcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xfs.flashcard.R
import kotlinx.android.synthetic.main.bottom_sheet_quit_cards.*

class QuitCardsBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_quit_cards, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }
    private fun setupView() {
        setBottomSheetAlwaysExpanded()
        ResumeButton.setOnClickListener {
            dismiss()
        }
        QuitButton.setOnClickListener {

        }
    }
    public fun setBottomSheetAlwaysExpanded() {
        dialog?.setOnShowListener { dialog1 ->
            val bottomSheetDialog = dialog1 as BottomSheetDialog
            val bottomSheet: FrameLayout? =
                    bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            if (bottomSheet != null) {
                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.skipCollapsed = true
                BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

}