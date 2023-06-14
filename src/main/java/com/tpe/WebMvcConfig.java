package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe") //defaultda da: com.tpe
@EnableWebMvc //WebMvc yi aktif et
public class WebMvcConfig implements WebMvcConfigurer {

    //view name e karsilik gelen jsp dosyasinin cozumlenmesini saglayan: view resolver'
    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class); //JavastandartTagLibrary: JSP dosyalari icinde HTML taglerini kullanarak
                                               //daha az kod yazmamizi saglar.
        resolver.setPrefix("/WEB-INF/views/"); //view dosyalari nerede(dizin)
        resolver.setSuffix(".jsp"); //view dosyalarinin uzantisi
        return resolver;
    }

    //css,image statik olan kaynaklarin dispatcher servleta gonderilmesine gerek yok


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**"). //bu pathdeki dosyalari statik olarak sun
                 addResourceLocations("/resources/").
                 setCachePeriod(0); //cacheleme icin belirli bir periyod suresi verilebilir
    }
}
