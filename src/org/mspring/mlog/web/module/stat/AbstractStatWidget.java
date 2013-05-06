/**
 * 
 */
package org.mspring.mlog.web.module.stat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.mspring.mlog.web.module.AbstractWidget;
import org.mspring.platform.utils.RequestUtils;
import org.springframework.web.servlet.support.WebContentGenerator;

/**
 * @author Gao Youbo
 * @since 2012-11-16
 * @Description
 * @TODO
 */
public class AbstractStatWidget extends AbstractWidget {
    private static final Logger log = Logger.getLogger(AbstractStatWidget.class);

    /**
     * 验证请求的合法性
     * 
     * @param request
     * @return
     */
    protected boolean validateRequest(HttpServletRequest request) {
        if (!WebContentGenerator.METHOD_GET.equals(request.getMethod())) { // 必须为GET请求
            log.debug("only GET request allowed");
            return false;
        }
        if (!RequestUtils.validateRequest(request)) {
            log.debug("Origin null is not allowed by Access-Control-Allow-Origin.");
            return false;
        }
        return true;
    }
}
