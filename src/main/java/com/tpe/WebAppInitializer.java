package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml yerine bu classi kullaniriz.
//dispatcher servletin tanimlanmasi,
//configuration classlarinin yerini gosterme
//bu iki islem icin: AbstractAnnotationConfigDispatcherServletInitializer


public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//Dispatcher: Servlet WebAppContext -> Controller,Handler Mapping,View Resolver
//            Root WebAppContext -> DB ye erisim:repositories,services


    @Override //data erisim(hibernate/jdbc) icin gerekli config class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override //Controller,Handler Mapping,View Resolver(SpringMVC) ile ilgili config class
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    //http://localhost:8080/SpringMvc/....
    @Override //hangi url ile gelen istekler servlet tarafindan karsilanacak
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
