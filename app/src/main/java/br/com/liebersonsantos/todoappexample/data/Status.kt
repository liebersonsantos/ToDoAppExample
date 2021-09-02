package br.com.liebersonsantos.todoappexample.data

/**
 * Created by lieberson on 8/31/21.
 * @author lieberson.xsantos@gmail.com
 */
enum class Status {
    TODO,
    PROGRESS,
    DONE,
    UNDEFINED;

    companion object {
        fun safeValueOf(name: String): Status =
            values().find {
                it.name.equals(name, ignoreCase = true)
            } ?: UNDEFINED

        fun status() = arrayOf(
            TODO.name,
            PROGRESS.name,
            DONE.name
        )

    }
}