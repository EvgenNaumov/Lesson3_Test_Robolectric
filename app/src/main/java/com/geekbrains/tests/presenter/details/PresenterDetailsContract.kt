package com.geekbrains.tests.presenter.details

import android.content.Context
import android.os.Bundle
import android.view.View
import com.geekbrains.tests.presenter.PresenterContract
import com.geekbrains.tests.view.details.ViewDetailsContract

interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
    fun onAttach(view: ViewDetailsContract)
    fun onDetach()
}
