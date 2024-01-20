package di

import data.repository.BirdsLoaderRepositoryImpl
import domain.BirdsLoaderRepository
import org.koin.dsl.module
import screens.home_screen.BirdsScreenModel

val sharedModule = module {
    single<BirdsLoaderRepository> {
        BirdsLoaderRepositoryImpl()
    }

    factory { BirdsScreenModel(get()) }


}