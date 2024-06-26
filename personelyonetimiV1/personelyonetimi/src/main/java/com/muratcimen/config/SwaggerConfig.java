package com.muratcimen.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //Swagger aracılığıyla API belgelendirmesi oluştururken belirli bir paketin dışındaki tüm controller sınıflarını dökümante etmeyi sağlar.
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build();
    }

    //Swagger aracılığıyla oluşturulan API belgelendirmesinde kullanıcıya API hakkında bilgi sağlamak için kullanılır
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Personel Yönetim Sistemi")
                .description("Personel Yönetim Sistemi, bir şirketin çalışanlarının temel bilgilerini saklayan " +
                        "ve yöneten bir web uygulamasıdır. Bu sistem, şirketin insan kaynakları departmanının günlük " +
                        "iş akışını yönetmek ve çalışan verilerini etkin bir şekilde izlemek için tasarlanmıştır.")
                .version("V1.0").build();
    }
}
