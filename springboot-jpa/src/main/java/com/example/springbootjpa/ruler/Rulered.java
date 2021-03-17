package com.example.springbootjpa.ruler;

import com.zouqi.dataflow.jpa.entity.RulerTemplate;
import com.zouqi.dataflow.service.hook.BeanHook;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public interface Rulered {
    Logger log = LoggerFactory.getLogger(Rulered.class);
    String getRulers();

    Long getId();

    default String getParsedRulers() {
        return parse(getRulers());
    }

    // rulers宏展开
    static String parse(String origin) {
        log.debug("beforeParse:{}",origin);
        while (origin.contains("{")&& origin.contains("}")) {
            var index1 = origin.indexOf("{");
            var index2 = origin.indexOf("}");
            Assert.isTrue(index2 > index1,"规则配置错误");
            var templateName = origin.substring(index1+1,index2);
            var templateContent = BeanHook.self().getRulerTemplateRepository().spec()
                    .eq("name",templateName)
                    .findOne()
                    .map(RulerTemplate::getContent)
                    .orElse(null);
            if (templateContent==null) return "false";
            origin = origin.replace("{"+templateName+"}",templateContent);
        }
        log.debug("afterParse:{}",origin);
        return origin;
    }

    // 检查改条rulered是否匹配
    default Boolean match() {
        //log.debug("Rulered实体id:{}",getId());
        return BeanHook.self().getRulerManager().match(getParsedRulers());
    }
}
