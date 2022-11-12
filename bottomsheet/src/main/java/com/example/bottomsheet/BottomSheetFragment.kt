package com.example.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottom_sheet, container, false)
//        val btnOK: Button = view.findViewById(R.id.btnOK)
//        val btnClose: Button = view.findViewById(R.id.btnClose)
//
//        btnOK.setOnClickListener {
//            Toast.makeText(mContext, "확인", Toast.LENGTH_SHORT).show()
//            dismiss()
//        }
//
//        btnClose.setOnClickListener {
//            Toast.makeText(mContext, "닫기", Toast.LENGTH_SHORT).show()
//            dismiss()
//        }

        return view
    }
}