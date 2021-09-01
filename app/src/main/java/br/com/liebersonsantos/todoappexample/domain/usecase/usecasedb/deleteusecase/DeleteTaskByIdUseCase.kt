package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
interface DeleteTaskByIdUseCase {
    suspend operator fun invoke(id: Int)
}