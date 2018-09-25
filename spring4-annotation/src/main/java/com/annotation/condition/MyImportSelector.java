package com.annotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑 返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    //返回值，就是要导入到容器中的组件的全类名
    //AnnotationMetadata: 当前标注@Import注解的类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //importingClassMetadata.get

        //这个方法，不能返回null,因为下面代码会取length，可以返回空数组
        return new String[]{"com.anno.bean.Blue","com.anno.bean.Yellow"};
    }
}
