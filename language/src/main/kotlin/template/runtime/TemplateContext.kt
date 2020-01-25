package template.runtime

import com.oracle.truffle.api.TruffleLanguage
import template.TemplateLanguage

class TemplateContext(val language: TemplateLanguage, val env: TruffleLanguage.Env) {
}