package com.liferay.workshop.dynamic.include;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = DynamicInclude.class)
public class ThemeDynamicInclude implements DynamicInclude {

    @Override
    public void include(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) throws IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println("<h1>Added by custom dynamic include.</h1>");
    }

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
        dynamicIncludeRegistry.register("/html/common/themes/bottom.jsp#pre");
    }
}
