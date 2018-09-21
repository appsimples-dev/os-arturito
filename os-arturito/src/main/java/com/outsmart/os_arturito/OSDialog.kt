package com.outsmart.os_arturito

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface


/**
 * Created by Mutti on 28/09/16.
 */

open class OSDialog {

    companion object {
        var dialog: AlertDialog? = null

        fun showAlertDialog(context: Context, message: Int) {

        }

        fun showAlertDialog(context: Context, message: String, button: String = "Ok", onDismiss: (() -> Unit)? = null): AlertDialog {
            return AlertDialog.Builder(context).setMessage(message)
                    .setNeutralButton(button, { dialogInterface, i -> dialogInterface.dismiss() })
                    .setOnDismissListener { onDismiss?.invoke() }
                    .show()
        }
    }

}
