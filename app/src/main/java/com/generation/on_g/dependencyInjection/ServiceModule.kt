package com.generation.on_g.dependencyInjection

import com.generation.on_g.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    //Indicar que os metodos que criaremos retornará uma instância de objeto
    @Provides
    //Indicando o objeto que estamos criando vai ser é singleton (Único)
    @Singleton
    fun provideRepository(): Repository{
        return Repository()
    }

}