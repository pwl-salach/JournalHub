package com.salach.journalhub.ui.screens.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.screens.pages.components.JournalPageBottomBar

class Pagination(
    private val journalId: Int,
) {
    private var prevPageId: Long = -1L
    private var nextPageId: Long = -1L
    private var currentIndex = 0

    fun findPage(journalPages: List<Page>, pageId: Long){
        for ((index, iterPage) in journalPages.withIndex()){
            if(iterPage.id == pageId){
                currentIndex = index
                if(journalPages.count() > index + 1){
                    nextPageId = journalPages[index + 1].id!!
                }
                break
            } else {
                prevPageId = iterPage.id!!
            }
        }
    }

    private fun getUriToPrevPage(): String {
        return buildUriToPage(prevPageId)
    }

    private fun getUriToNextPage(): String {
        return buildUriToPage(nextPageId)
    }

    private fun buildUriToPage(pageId: Long): String {
        return "${Graph.NOTE_PAGE}?journalId=${journalId}&pageId=${pageId}"
    }

    @Composable
    fun Draw(navController: NavHostController, count: Int){
        JournalPageBottomBar(
            currentIndex = currentIndex,
            pagesCount = count,
            viewPrevious = {
                navController.navigate(getUriToPrevPage())
            },
            viewNext = {
                navController.navigate(getUriToNextPage())
            }
        )
    }
}