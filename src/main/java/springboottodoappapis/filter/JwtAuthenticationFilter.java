package springboottodoappapis.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboottodoappapis.services.CustomUserDetailsService;
import springboottodoappapis.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearer = request.getHeader("Authorization");
        String username = null;
        String password = null;

        if( bearer != null && bearer.startsWith("Bearer") ){
            String token = bearer.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername( username );
                if( username != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                    UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities() );
                    upat.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
                    SecurityContextHolder.getContext().setAuthentication( upat );
                }else{
                    System.out.println("Invalid Token");
                }
            }catch (Exception e){

            }
        }else{
            System.out.println("Invalid Bearer Token Format !!!");
        }

        filterChain.doFilter(request, response);
    }
}
