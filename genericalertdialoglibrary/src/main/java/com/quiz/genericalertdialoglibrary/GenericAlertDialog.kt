package com.quiz.genericalertdialoglibrary

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.quiz.genericalertdialoglibrary.enums.AlertStatus

class GenericAlertDialog {
    companion object {

        private var isShowWarningDialog: Boolean = false
        private var isCancelable: Boolean = false

        fun cancelable(cancelable: Boolean): Companion {
            this.isCancelable = cancelable
            return this
        }

        /**
         * Default One ACTION
         */
        fun show(context: Context?): Companion {
            show(context, null, null, null)
            return this
        }

        /**
         * Default One ACTION WITH Msg
         */
        fun show(context: Context?, message: String?) {
            show(context, null, null, null)
        }

        fun show(
            context: Context?,
            alertStatus: AlertStatus?,
            message: String?
        ) {
            show(
                context,
                alertStatus,
                null,
                message
            )
        }

        fun show(
            context: Context?,
            alertStatus: AlertStatus?,
            warningDialogListener: DialogListener?,
            vararg textArr: String?
        ) {
            try {
                if (isShowWarningDialog)
                    return

                var message = context?.resources?.getString(R.string.message)
                if (!textArr.getOrNull(0).isNullOrEmpty()) {
                    message = textArr.getOrNull(0)
                }

                var positiveText = context?.resources?.getString(R.string.positive)
                if (!textArr.getOrNull(1).isNullOrEmpty()) {
                    positiveText = textArr.getOrNull(1)
                }

                var negativeText = context?.resources?.getString(R.string.negative)
                if (!textArr.getOrNull(2).isNullOrEmpty()) {
                    negativeText = textArr.getOrNull(2)
                }

                var neutralText = context?.resources?.getString(R.string.neutral)
                if (!textArr.getOrNull(3).isNullOrEmpty()) {
                    neutralText = textArr.getOrNull(3)
                }

                var title = context?.resources?.getString(R.string.warning)
                if (!textArr.getOrNull(4).isNullOrEmpty()) {
                    title = textArr.getOrNull(4)
                }

                context?.let {
                    val alertDialogBuilder = AlertDialog.Builder(it)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(isCancelable)
                    when (alertStatus) {
                        AlertStatus.ONEACTION -> {
                            alertDialogBuilder
                                .setPositiveButton(positiveText)
                                { _, _ ->
                                    warningDialogListener?.onPositiveButton(alertDialogBuilder.create())
                                }
                        }
                        AlertStatus.TWOACTION -> {

                            alertDialogBuilder
                                .setPositiveButton(positiveText)
                                { _, _ ->

                                    warningDialogListener?.onPositiveButton(alertDialogBuilder.create())
                                }
                                .setNegativeButton(negativeText) { _, _ ->

                                    warningDialogListener?.onNegativeButton(alertDialogBuilder.create())
                                }
                        }
                        AlertStatus.THREEACTION -> {

                            alertDialogBuilder
                                .setPositiveButton(positiveText)
                                { _, _ ->

                                    warningDialogListener?.onPositiveButton(alertDialogBuilder.create())
                                }
                                .setNegativeButton(negativeText) { _, _ ->

                                    warningDialogListener?.onNegativeButton(alertDialogBuilder.create())
                                }
                                .setNeutralButton(neutralText) { _, _ ->

                                    warningDialogListener?.onNeutralButton(alertDialogBuilder.create())
                                }
                        }
                        else -> {
                            alertDialogBuilder
                                .setPositiveButton(positiveText)
                                { _, _ ->
                                    warningDialogListener?.onPositiveButton(alertDialogBuilder.create())
                                }
                        }
                    }

                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.setOnDismissListener {
                        defaultValues()
                    }
                    alertDialog.show()
                    isShowWarningDialog = true
                }
            } catch (e: Exception) {
            }
        }

        private fun defaultValues() {
            isShowWarningDialog = false
            isCancelable = false
        }

        interface DialogListener {
            fun onPositiveButton(dialog: AlertDialog) {}
            fun onNegativeButton(create: AlertDialog) {}
            fun onNeutralButton(create: AlertDialog) {}
        }
    }
}