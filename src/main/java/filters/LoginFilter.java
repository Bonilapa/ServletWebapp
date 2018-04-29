package filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value="/login")
public class LoginFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) {

        String userLogin = (String) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userName");

        if (userLogin == null) {

            LOGGER.debug("LoginFilter. userLogin = null");

            try {

                filterChain.doFilter(servletRequest, servletResponse);

            } catch (IOException e) {

                LOGGER.error("IOException. LoginFilter.doFilter().", e);

            } catch (ServletException e) {

                LOGGER.error("ServletException. LoginFilter.doFilter().", e);
            }

        }else{

            LOGGER.debug("LoginFilter. userLogin = " + userLogin);

            try {

                ((HttpServletResponse)servletResponse)
                        .sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/index");

            } catch (IOException e) {

                LOGGER.error("IOException. LoginFilter redirects to /index.", e);
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