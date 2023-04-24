package com.journey.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journey.domain.common.Result;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.journey.constants.CommonConstant.APPLICATION_JSON;

/**
 * web工具类
 *
 * @author hy
 * @version 1.0
 */
public class WebUtil {

    public static void render(HttpServletResponse response, Integer status, String message) {
        try {
            response.setStatus(status);
            response.setContentType(APPLICATION_JSON);
            response.getWriter().println(new ObjectMapper().writeValueAsString(Result.fail(status, message)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void render(HttpServletResponse response, Result result) throws Exception {
        try {
            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().println(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}