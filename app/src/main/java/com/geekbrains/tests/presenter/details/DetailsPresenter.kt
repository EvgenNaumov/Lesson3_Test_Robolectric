package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.details.ViewDetailsContract

class DetailsPresenter (
    private var count: Int = 0
) : PresenterDetailsContract {

    var viewContract: ViewDetailsContract? = null
    set

    private var count_:Int = 0

    override fun setCounter(count: Int) {
        this.count = count
        count_ = count
        viewContract?.setCount(count)
    }

    override fun onIncrement() {
        count++
        viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract?.setCount(count)
    }

    override fun onAttach(view: ViewDetailsContract) {
        this.viewContract = view
        this.count = count_
        viewContract!!.setCount(count)

    }

    override fun onDetach() {
        count_ = this.count
        viewContract = null
    }
}
