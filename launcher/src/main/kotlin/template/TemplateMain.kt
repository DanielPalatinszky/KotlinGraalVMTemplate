package template

import org.graalvm.polyglot.Context
import org.graalvm.polyglot.PolyglotException
import org.graalvm.polyglot.Source
import java.io.File
import kotlin.system.exitProcess

private const val TEMPLATE = "template"

fun main(args: Array<String>) {
    if (args.size != 1)
        throw Throwable("Bad arguments: a source file is needed!")

    val source = File(args[0]).readText()
    val sourceCode = Source.newBuilder(TEMPLATE, source, "source code").build()

    exitProcess(execute(sourceCode))
}

private fun execute(sourceCode: Source) : Int {
    val context: Context
    val errorOutput = System.err

    try {
        context = Context.newBuilder(TEMPLATE).`in`(System.`in`).out(System.out).build()
    } catch (exception: IllegalArgumentException) {
        errorOutput.println(exception.message!!)

        return 1
    }

    return try {
        val result = context.eval(sourceCode)

        println(result.toString())

        0
    } catch (exception: PolyglotException) {
        if (exception.isInternalError)
            exception.printStackTrace()
        else
            errorOutput.println(exception.message)

        1
    } finally {
        context.close()
    }
}