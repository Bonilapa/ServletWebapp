package filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value="/order")
public class OrderFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(OrderFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain){

        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userName");

        if (userLogin != null) {

            LOGGER.debug("OrderFilter. userLogin = " + userLogin);

            try {

                filterChain.doFilter(servletRequest, servletResponse);

            } catch (IOException e) {

                LOGGER.error("IOException. OrderFilter.doFilter().", e);
            } catch (ServletException e) {

                LOGGER.error("ServletException. OrderFilter.doFilter().", e);
            }

        }else{

            LOGGER.debug("LoginFilter. userLogin = null");

            try {
                ((HttpServletResponse)servletResponse)
                        .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/login");

            } catch (IOException e) {

                LOGGER.error("IOException. OrderFilter redirects to /login.", e);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

}