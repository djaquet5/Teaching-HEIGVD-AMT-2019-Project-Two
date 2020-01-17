package ch.heigvd.amt.api.security


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ch.heigvd.amt.api.business;
import ch.heigvd.amt.repositories;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IAuthorization authorization;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IAuthentication authentication;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        businessModel info = authorization.decodeToken(request.getHeader("Authorization"));

        if(info == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            request.setAttribute("auth-info", info);
            return true;
        }
    }
};