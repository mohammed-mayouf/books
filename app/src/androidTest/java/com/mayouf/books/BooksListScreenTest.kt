package com.mayouf.books

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BooksListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        // This call injects the Hilt dependencies before the test runs.
        hiltRule.inject()
    }

    @Test
    fun booksListScreen_displaysBooksAndShowsBottomSheet() {
        // Wait for the list to load. Our FakeBooksRepository should inject two books.
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("BookItemRow_Test_Mock Book A").fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Assert that the known book item is displayed.
        composeTestRule.onNodeWithTag("BookItemRow_Test_Mock Book A").assertIsDisplayed()

        // Perform click on the book item.
        composeTestRule.onNodeWithTag("BookItemRow_Test_Mock Book A").performClick()

        // Now, the bottom sheet should appear.
        composeTestRule.onNodeWithTag("BookDetailsSheet").assertIsDisplayed()

        // Verify that the cover image is shown.
        composeTestRule.onNodeWithTag("BookCoverImage_Mock Book A").assertIsDisplayed()
    }
}
