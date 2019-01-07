package com.virtusa.techtest.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.memtrip.mxandroid.*

abstract class MviActivity<VI : MxViewIntent, RA : MxRenderAction, VS : MxViewState, VL : MxViewLayout>
    : MxViewActivity<VI, RA, VS, VL>() {

    protected inline fun <reified T : ViewModel> getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}