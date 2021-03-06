/**
 * 
 */
package org.mspring.mlog.web.module.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mspring.mlog.web.push.DefaultWriterAppender;
import org.mspring.platform.web.freemarker.widget.stereotype.Widget;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gao Youbo
 * @since 2013-4-23
 * @description
 * @TODO
 */
@RequestMapping("/common")
@Widget
public class PushMessageWidget {

    @RequestMapping("/push")
    public void push(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "private");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Connection", "Keep-Alive");
        response.setHeader("Proxy-Connection", "Keep-Alive");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<!-- Comet is a programming technique that enables web servers to send data to the client without having any need for the client to request it. -->\n");
        writer.flush();

        if (!request.isAsyncSupported()) {
            // log.info("the servlet is not supported Async");
            return;
        }

        request.startAsync(request, response);
        if (request.isAsyncStarted()) {
            final AsyncContext asyncContext = request.getAsyncContext();
            asyncContext.setTimeout(1L * 60L * 1000L);// 60sec

            asyncContext.addListener(new AsyncListener() {
                public void onComplete(AsyncEvent event) throws IOException {
                    DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
                }

                public void onTimeout(AsyncEvent event) throws IOException {
                    DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
                }

                public void onError(AsyncEvent event) throws IOException {
                    DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.remove(asyncContext);
                }

                public void onStartAsync(AsyncEvent event) throws IOException {
                }
            });
            DefaultWriterAppender.ASYNC_CONTEXT_QUEUE.add(asyncContext);
        } else {
            // log.error("the ruquest is not AsyncStarted !");
        }

    }
}
