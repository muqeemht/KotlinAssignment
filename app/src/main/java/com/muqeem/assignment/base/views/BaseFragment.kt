package com.example.kotlinrnd.base.views

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.kotlinrnd.R
import com.muqeem.assignment.base.models.RequestStatus
import com.example.kotlinrnd.base.utils.CommonUtils
import com.example.kotlinrnd.base.viewmodels.BaseVewModel

open class BaseFragment : Fragment(),  View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity?
    }



    open fun setupNetworkListeners(viewModel: BaseVewModel) {

        viewModel.showHideLoading.observe(viewLifecycleOwner, Observer<Boolean> { aBoolean ->
            if (aBoolean) {
                LoadingDialog.showLoading(mActivity)
            } else {
                LoadingDialog.disDialog()
            }
        })

        viewModel.connectionError.observe(viewLifecycleOwner, Observer<Boolean> { aBoolean ->
            CommonUtils.showToastMessage(getString(R.string.no_internet_msg))
        })


        viewModel.requestStatus.observe(viewLifecycleOwner, Observer<RequestStatus> { requestStatus ->
            if (requestStatus.isError()) {
                CommonUtils.showToastMessage(requestStatus.getErrorMessage())
                LoadingDialog.disDialog()
            }
        })

    }

}
