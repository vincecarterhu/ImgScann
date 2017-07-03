package com.vc.library.imgbrowse.util;

import java.util.regex.Pattern;

/**
 * Created by vc on 2017/7/3.
 */

public class BaseUtil {


    public static boolean isHttp(String str) {
        Pattern pattern = Pattern
                .compile(
                        "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?",
                        Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(str).matches()) {
            return true;
        } else {
            return false;
        }
    }


}
