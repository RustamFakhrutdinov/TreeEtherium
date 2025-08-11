package com.practicum.denettest.di

import com.practicum.denettest.data.AppDatabase
import com.practicum.denettest.data.AppMetaRepository
import com.practicum.denettest.data.NodeRepository
import com.practicum.denettest.domain.*
import com.practicum.denettest.presentation.TreeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {
        AppDatabase.getDatabase(androidContext())
    }
    single { get<AppDatabase>().nodeDao() }
    single { get<AppDatabase>().appMetaDao() }

    single { NodeRepository(get()) }
    single { AppMetaRepository(get()) }

    single { GetNodeUseCase(get()) }
    single { AddNodeUseCase(get()) }
    single { DeleteNodeUseCase(get()) }
    single { GetAppMetaUseCase(get()) }
    single { SetAppMetaUseCase(get()) }
    single { GenerateAddressUseCase(get()) }

    viewModel { TreeViewModel(
        get(), // GetNodeUseCase
        get(), // AddNodeUseCase
        get(), // DeleteNodeUseCase
        get(), // GetAppMetaUseCase
        get(), // SetAppMetaUseCase
        get()  // GenerateAddressUseCase
    ) }
}