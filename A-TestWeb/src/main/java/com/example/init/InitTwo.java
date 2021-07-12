package com.example.init;

import lombok.val;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitTwo implements ApplicationRunner {

    /**
     * java -jar MyProject.jar --Mygroup=a,b --filePath=c:/data/1.txt -myargs=2 testNoArg TestNoarg2=3
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application Runner !!!");
        val sourceArgs = args.getSourceArgs();
        val optionNames = args.getOptionNames();
        val nonOptionArgs = args.getNonOptionArgs();
        for (String sourceArg : sourceArgs) {
            System.out.println(sourceArg);
        }
        for (String optionName : optionNames) {
            System.out.println(optionName);
        }
        for (String nonOptionArg : nonOptionArgs) {
            System.out.println(nonOptionArg);
        }
        /**
         * Application Runner !!!
         * --Mygroup=a,b
         * --filePath=c:/data/1.txt
         * -myargs=2
         * testNoArg
         * TestNoarg2=3
         * filePath
         * Mygroup
         * -myargs=2
         * testNoArg
         * TestNoarg2=3
         */
    }
}
