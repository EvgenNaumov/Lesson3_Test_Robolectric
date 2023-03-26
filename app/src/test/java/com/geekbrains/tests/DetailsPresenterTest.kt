package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.widget.Button
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.details.PresenterDetailsContract
import com.geekbrains.tests.view.details.DetailsActivity
import com.nhaarman.mockito_kotlin.timeout
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {

    private val detailsPresenter = DetailsPresenter(0)

    @Mock
    lateinit var presenterDetailsContract: PresenterDetailsContract

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun presenter_Increment() {
        scenario.onActivity{
            val decrementButton = it.findViewById<Button>(R.id.decrementButton)
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            decrementButton.performClick()

            assertEquals(TEST_NUMBER_OF_RESULTS_MINUS_1, totalCountTextView)
        }


    }

    @Test
    fun presenter_onDecrementTest(){
        detailsPresenter.onDecrement()
        verify(presenterDetailsContract.onDecrement(), timeout(1))
    }

    @Test
    fun presenter_OnAttachTest(){
        val view = mock(DetailsActivity::class.java)
        presenterDetailsContract.onAttach(view)
        assertNotNull(detailsPresenter.viewContract)
    }

    @Test
    fun presenter_OnDetachTest(){
        presenterDetailsContract.onDetach()
        assertNull(detailsPresenter.viewContract)
    }

    @After
    fun close() {
        scenario.close()
    }


}