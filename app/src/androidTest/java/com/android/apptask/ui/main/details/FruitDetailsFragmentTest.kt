package com.android.apptask.ui.main.details

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.android.apptask.R
import com.android.apptask.domain.models.Fruit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FruitDetailsFragmentTest {

    private var fruit = Fruit(
        type = "Apple",
        price = 15.0,
        weight = 2.5
    )

    @Before
    fun setUp() {

        launchFragmentInHiltContainer<FruitDetailsFragment>(
            themeResId = R.style.Theme_AppTask,
            fragmentArgs = bundleOf(
                "type" to fruit.type.toString(),
                "price" to fruit.price.toString(),
                "weight" to fruit.weight.toString(),
            )
        ) {

        }

    }


    @Test
    fun checkFruitNameDisplayed() {

        onView(withId(R.id.name)).check(matches(withText(fruit.type)))

    }


    @Test
    fun checkFruitPriceDisplayed() {

        onView(withId(R.id.price)).check(matches(withText(fruit.price.toString())))

    }


    @Test
    fun checkFruitWeightDisplayed() {

        onView(withId(R.id.weight)).check(matches(withText(fruit.weight.toString())))

    }

}