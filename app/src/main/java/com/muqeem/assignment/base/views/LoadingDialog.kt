package com.example.kotlinrnd.base.views

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlinrnd.R

class LoadingDialog {
    companion object {
        private var mLoadingDialog: Dialog? = null

        fun showLoading(context: Activity, msg: String?, cancelable: Boolean): Dialog? {
            if (mLoadingDialog != null) {
                disDialog()
            }
            val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
            val loadingText = view.findViewById<View>(R.id.id_tv_loading_dialog_text) as TextView
            loadingText.text = msg
            mLoadingDialog = Dialog(context, R.style.CustomProgressDialog)
            mLoadingDialog!!.setCancelable(cancelable)
            mLoadingDialog!!.setCanceledOnTouchOutside(false)
            mLoadingDialog!!.setContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
            if (!context.isFinishing && !context.isDestroyed) mLoadingDialog!!.show()
            return mLoadingDialog
        }

        fun showLoading(context: Activity?): Dialog? {
            if (mLoadingDialog != null) {
                disDialog()
            }
            if (context != null) {
                val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
                val loadingText = view.findViewById<View>(R.id.id_tv_loading_dialog_text) as TextView
                loadingText.text = "加载中..."
                mLoadingDialog = Dialog(context, R.style.CustomProgressDialog)
                mLoadingDialog!!.setCancelable(true)
                mLoadingDialog!!.setCanceledOnTouchOutside(false)
                mLoadingDialog!!.setContentView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                if (!context.isFinishing && !context.isDestroyed) mLoadingDialog!!.show()
            }
            return mLoadingDialog
        }

        /**
         * 关闭加载对话框
         */
        fun disDialog() {
            if (mLoadingDialog != null) {
                mLoadingDialog!!.cancel()
                mLoadingDialog = null
            }
        }
    }
}