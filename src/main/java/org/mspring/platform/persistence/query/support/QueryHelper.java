/**
 * 
 */
package org.mspring.platform.persistence.query.support;

import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class QueryHelper {
    public static boolean isValidHql(String hqlString) {
        boolean isBlank = StringUtils.isBlank(hqlString);
        if (isBlank) {
            return false;
        }
        return !hqlString.equals("()");
    }

    public static String qualifyHql(String str) {
        return str.replace('.', '_');
    }
}
