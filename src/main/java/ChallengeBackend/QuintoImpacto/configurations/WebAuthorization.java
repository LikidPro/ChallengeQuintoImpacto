package ChallengeBackend.QuintoImpacto.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()

                .antMatchers("/rest/**").hasAuthority("ADMIN")
                .antMatchers("/admin/admin.html").hasAuthority("ADMIN")
                .antMatchers("/admin/admin.js").hasAuthority("ADMIN")
                .antMatchers("/admin/admin.css").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("login/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "api/*").hasAuthority("ADMIN")

        ;

       http.formLogin()
                .usernameParameter("email")
                .passwordParameter("contraseña")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");


        http.csrf().disable();


        http.headers().frameOptions().disable();


        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> {

            if (req.getRequestURI().contains("admin")) {
                res.sendRedirect("/login.html");
            }
        });

        http.formLogin().successHandler((req, res, auth) -> {clearAuthenticationAttributes(req);});


        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));


        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());



    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }


    }


