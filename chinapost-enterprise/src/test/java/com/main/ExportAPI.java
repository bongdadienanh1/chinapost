package com.main;

import com.ylife.utils.PackageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.List;

/**
 * Created by InThEnd on 2016/6/22.
 * API 导出
 */
public class ExportAPI {

    public static void main(String[] args) throws Exception {
        PackageHelper helper = new PackageHelper("com.ylife.chinapost.controller");
        List<Class<?>> list = helper.getClasses();
        for (Class<?> clazz : list) {
            if (clazz.isAnnotationPresent(Controller.class) && clazz.isAnnotationPresent(RequestMapping.class)) {
                String fileName = clazz.getSimpleName() + "." + "txt";
                File file = new File("F:\\" + fileName);
                file.createNewFile();
                System.out.println(fileName);
            }
        }
    }
}
