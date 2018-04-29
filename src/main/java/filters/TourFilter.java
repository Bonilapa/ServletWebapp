package filters;

import connector.HelloServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value="/tour")
public class TourFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(TourFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) {

        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userName");

        if (userLogin != null) {

            LOGGER.debug("OrderFilter. userLogin = " + userLogin);

            try {

                filterChain.doFilter(servletRequest, servletResponse);

            } catch (IOException e) {

                LOGGER.error("IOException. TourFilter.doFilter().", e);
            } catch (ServletException e) {

                LOGGER.error("ServletException. TourFilter.doFilter().", e);
            }

        } else {

            LOGGER.debug("LoginFilter. userLogin = null");

            try {

                ((HttpServletResponse) servletResponse)
                        .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/login");

            } catch (IOException e) {

                LOGGER.error("IOException. TourFilter redirects to /login.", e);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void destroy() {

    }
}