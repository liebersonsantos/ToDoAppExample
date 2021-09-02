package br.com.liebersonsantos.todoappexample.extensions

import br.com.liebersonsantos.todoappexample.data.model.TaskDate
import org.threeten.bp.OffsetDateTime

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
fun OffsetDateTime.converterToDate(): TaskDate =
    TaskDate(
        day = dayOfMonth,
        month = monthValue,
        year = year,
        hour = hour,
        minute = minute
    )