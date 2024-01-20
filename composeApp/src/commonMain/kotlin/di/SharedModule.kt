package di

import data.repository.BirdsLoaderRepositoryImpl
import domain.BirdsLoaderRepository
import org.koin.dsl.module

val sharedModule = module {
    single<BirdsLoaderRepository> {
        BirdsLoaderRepositoryImpl()
    }

}