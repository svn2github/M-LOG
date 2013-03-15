/**
 * 
 */
package org.mspring.platform.persistence.query.converter;

import org.mspring.platform.utils.DateUtils;
import org.mspring.platform.utils.StringUtils;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class DateConverter implements Converter {

    /* (non-Javadoc)
     * @see org.mspring.platform.dao.query.converter.Converter#convert(java.lang.Class, java.lang.String)
     */
    public Object convert(Class type, String value) throws ConversionException {
        // TODO Auto-generated method stub
        if (StringUtils.isBlank(value)) {
            return null;
        }
        
        int datelength = value.length();
        String pattern = "";
        if (datelength > 10) {
            pattern = DateUtils.YYYY_MM_DD_HH_MM_SS;
        }
        else if (datelength == 10){
            pattern = "yyyy-MM-dd";
        }
        else {
            pattern = "yyyyMMdd";
        }
        return DateUtils.parse(value, pattern);
    }

}
