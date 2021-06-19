package medpod.blood.config

import javax.servlet.*
import javax.servlet.http.HttpServletResponse

class AddResponseHeaderFilter : Filter {
    override fun doFilter(
        request: ServletRequest?, response: ServletResponse,
        chain: FilterChain
    ) {
        val httpServletResponse: HttpServletResponse = response as HttpServletResponse
        httpServletResponse.setHeader(
            "Access-Control-Allow-Origin", "*"
        )
        chain.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {}

    override fun destroy() {}

}