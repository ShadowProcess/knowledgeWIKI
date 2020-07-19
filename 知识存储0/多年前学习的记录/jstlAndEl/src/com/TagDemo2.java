package com;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 自定义带主体的标签
 */

public class TagDemo2 extends SimpleTagSupport {

    PageContext pc;

    public TagDemo2() {
        super();
    }

    @Override
    public void doTag() throws JspException, IOException {
        //获取标签主体
        JspFragment jf = getJspBody();
        //执行输出标签体中的内容
        jf.invoke(pc.getOut()); //jf.invoke(null); 这样写也可以，因为它默认是以out输出的
    }

    @Override
    public void setParent(JspTag parent) {
        super.setParent(parent);
    }

    @Override
    public JspTag getParent() {
        return super.getParent();
    }

    @Override
    public void setJspContext(JspContext pc) {
        this.pc = (PageContext) pc;

    }

    @Override
    protected JspContext getJspContext() {

        return super.getJspContext();
    }

    @Override
    public void setJspBody(JspFragment jspBody) {
        super.setJspBody(jspBody);
    }

    @Override
    protected JspFragment getJspBody() {
        return super.getJspBody();
    }



}
