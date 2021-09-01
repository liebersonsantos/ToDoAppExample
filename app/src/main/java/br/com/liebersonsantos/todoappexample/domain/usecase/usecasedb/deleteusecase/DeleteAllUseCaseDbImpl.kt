package br.com.liebersonsantos.todoappexample.domain.usecase.usecasedb.deleteusecase

import br.com.liebersonsantos.todoappexample.data.db.repository.DbRepository
import javax.inject.Inject

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
class DeleteAllUseCaseDbImpl @Inject constructor(private val dbRepository: DbRepository):
    DeleteAllUseCaseDb {
    override suspend fun invoke() = dbRepository.deleteAll()
}