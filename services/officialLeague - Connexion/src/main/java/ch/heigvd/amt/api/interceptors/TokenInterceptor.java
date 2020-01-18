package ch.heigvd.amt.api.interceptors;

import ch.heigvd.amt.api.service.DecodedToken;
import ch.heigvd.amt.api.service.JWTToken;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JWTToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");

        try {
            DecodedToken decodedToken = jwtToken.verifyToken(header);
            request.setAttribute("token", decodedToken);

            return true;
        } catch (JWTVerificationException | NullPointerException e) {
            System.out.println(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return false;
        }
    }
}
