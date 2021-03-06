package ma.emsi.patientmvc.sec;

import ma.emsi.patientmvc.sec.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder.encode("1234")).roles("USER")

                .and()
                .withUser("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");
                //$2a$10$gDUkbe90OFmsTOij9b5JT.NIyElxi65syXB6Bqoj0dxg2CBooji/y pass 1234
*/
        //autentification avec jdbcAuthentication
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal,role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);
*/
        //spring sec va delege la confirmation au service userDetailsService
        auth.userDetailsService(userDetailsService);



    }
    //specifier les droit d'acces
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//indique a spring sec quant va utuliser formulaire dautantifaication
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers( "/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers( "/user/**").hasAuthority("USER");
        //autoriser les webjars pour toulmande
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        //min 42.30

        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

}
