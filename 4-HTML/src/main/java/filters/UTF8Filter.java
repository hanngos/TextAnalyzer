package filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter used to set the character encoding as UTF-8.
 *
 * @author Hanna Gościniak
 * @version 1.0
 */
@WebFilter("/*")
public class UTF8Filter implements javax.servlet.Filter {

    /**
     * Public method that sets the character set for the request and passes the request on
     *
     * @param request  servlet request
     * @param response servlet response
     * @param chain    filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
