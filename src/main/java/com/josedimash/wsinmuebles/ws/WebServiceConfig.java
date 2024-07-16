package com.josedimash.wsinmuebles.ws;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import com.josedimash.wsinmuebles.interceptor.WebServiceInterceptor;

import java.util.List;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {
    private final
    WebServiceInterceptor webServiceInterceptor;

    public WebServiceConfig(WebServiceInterceptor webServiceInterceptor) {
        this.webServiceInterceptor = webServiceInterceptor;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/WSInmuebles/*");
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors)  {
        try {
            interceptors.add(webServiceInterceptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
      webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/WSInmuebles");
    }
}
