package com.salach.journalhub.navigation.graphs

import androidx.navigation.NamedNavArgument


object NavRouteName {
    const val dashboard = "dashboard"
    const val journals = "journals"
    const val settings = "settings"
    const val addJournalInit = "addJournalInit"
    const val addJournalPickColor = "addJournalPickColor"
    const val addJournalPickIcon = "addJournalPickIcon"
    const val addJournalPickIconColor = "addJournalPickIconColor"
}

sealed class Route(val link: String, val arguments: List<NamedNavArgument> = emptyList()){
    object Dashboard: Route(NavRouteName.dashboard)
    object Journals: Route(NavRouteName.journals)
    object Settings: Route(NavRouteName.settings)
    object AddJournalInit: Route(NavRouteName.addJournalInit)
    object AddJournalPickColor: Route(NavRouteName.addJournalPickColor)
    object AddJournalPickIcon: Route(NavRouteName.addJournalPickIcon)
    object AddJournalPickIconColor: Route(NavRouteName.addJournalPickIconColor)
}
