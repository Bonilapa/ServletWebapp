package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value="/tour")
public class TourFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userName");
        if (userLogin != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}