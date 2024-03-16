package co.com.nisum.config;

import co.com.nisum.model.user.gateways.UserGatewayRepository;
import co.com.nisum.usecase.user.UserUseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.nisum.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        UserUseCase userUseCase(UserGatewayRepository userGatewayRepository){
                return new UserUseCase(userGatewayRepository);
        }
}
