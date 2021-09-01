package br.com.liebersonsantos.todoappexample.data.model

/**
 * Created by lieberson on 9/1/21.
 * @author lieberson.xsantos@gmail.com
 */
data class TaskDate(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0,
    val hour: Int = 0,
    val minute: Int = 0
) {
    fun isDateReady(): Boolean = day != 0 && month != 0 && year != 0

    fun isTimeReady(): Boolean = hour != 0

    fun getDateTimeSet(): String = "$day/$month/$year - $hour:$minute"
}