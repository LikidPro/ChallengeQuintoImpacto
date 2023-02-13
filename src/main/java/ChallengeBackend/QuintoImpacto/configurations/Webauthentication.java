package ChallengeBackend.QuintoImpacto.configurations;
import ChallengeBackend.QuintoImpacto.modelos.Alumno;
import ChallengeBackend.QuintoImpacto.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class Webauthentication extends GlobalAuthenticationConfigurerAdapter{
    @Autowired
    AlumnoRepositorio alumnoRepositorio;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(inputName-> {
            Alumno alumno = alumnoRepositorio.findByEmail(inputName);
            if (alumno != null && alumno.isEstadoAlumno()==true){
                if (alumno.getEmail().contains("admin@admin.com")){
                    return new User(alumno.getEmail() , alumno.getContraseña(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                  }
                else {
                return new User(alumno.getEmail() , alumno.getContraseña(),
                AuthorityUtils.createAuthorityList("ALUMNO"));


            }}else{
                throw new UsernameNotFoundException(
                        "Unknown user:" + inputName
                );
        }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
}
