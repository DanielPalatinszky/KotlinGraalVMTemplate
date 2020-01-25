package template

import com.oracle.truffle.api.CallTarget
import com.oracle.truffle.api.TruffleLanguage
import template.runtime.TemplateContext

@TruffleLanguage.Registration(
    id = TemplateLanguage.ID,
    name = "Template",
    defaultMimeType = TemplateLanguage.MIME_TYPE,
    characterMimeTypes = [TemplateLanguage.MIME_TYPE],
    contextPolicy = TruffleLanguage.ContextPolicy.SHARED
)
class TemplateLanguage : TruffleLanguage<TemplateContext>() {
    companion object {
        const val ID = "template"
        const val MIME_TYPE = "application/x-template"
    }

    override fun createContext(env: Env?): TemplateContext = TemplateContext(this, env!!)

    override fun isObjectOfLanguage(`object`: Any?) = false

    override fun parse(request: ParsingRequest?): CallTarget {
        return super.parse(request)
    }
}