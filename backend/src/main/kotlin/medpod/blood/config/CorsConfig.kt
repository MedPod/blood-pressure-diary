package medpod.blood.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MyConfiguration {

    @Bean
    fun corsBean(): FilterRegistrationBean<AddResponseHeaderFilter> {
        return FilterRegistrationBean(AddResponseHeaderFilter())
    }
}