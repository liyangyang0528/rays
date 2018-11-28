package com.lyyco.rays.service.thinkinginjava;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Author liyangyang
 * 2018/11/28
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }
    }

    private static class DirFilter implements FilenameFilter {
        private Pattern pattern;

        public DirFilter(String arg) {
            pattern = Pattern.compile(arg);
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }
}
