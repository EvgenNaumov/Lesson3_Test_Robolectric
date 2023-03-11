package com.geekbrains.tests

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.VerificationModes
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.details.PresenterDetailsContract
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    @Mock
    private lateinit var view:ViewDetailsContract

    private val detailsPresenter = DetailsPresenter(0)

    @Mock
    lateinit var presenterDetailsContract: PresenterDetailsContract

    @Before
    fun setup() {
        view = DetailsActivity()
        MockitoAnnotations.initMocks(this)


    }

    @Test
    fun presenter_OnAttach() {
        detailsPresenter.onAttach(view)
        verify(presenterDetailsContract, times((1))).onAttach(view)
    }
}