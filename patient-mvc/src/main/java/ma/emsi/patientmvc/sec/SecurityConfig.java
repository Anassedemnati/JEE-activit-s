package ma.emsi.patientmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder.encode("1234")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");
    }
    //specifier les droit d'acces
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//indique a spring sec quant va utuliser formulaire dautantifaication
        http.authorizeRequests().antMatchers( "/delete/**","/edit/**","/save/**","/formPatient/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers( "/index/**").hasRole("USER");
        //min 42.30

        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
