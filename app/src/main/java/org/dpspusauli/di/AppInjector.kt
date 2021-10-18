package org.dpspusauli.di


import org.dpspusauli.authenticaton.AuthViewModel
import org.dpspusauli.network.RestClient
import org.dpspusauli.network.RestClient.webServices
import org.dpspusauli.student.mvvm.ParentDashboardViewModel
import org.dpspusauli.student.mvvm.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { RestClient }
    single { webServices() }
}

val viewModelModule = module {
    viewModel {
        AuthViewModel(get())
    }
    viewModel {
        ProfileViewModel(get())
    }
    viewModel {
        ParentDashboardViewModel(get())
    }

}