package org.mspring.platform.persistence.query.criterion;

import org.mspring.platform.persistence.query.QueryBuilder;
import org.mspring.platform.persistence.query.support.QueryHelper;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class LikeExpression extends SimpleExpression {
    /**
     * 
     */
    private static final long serialVersionUID = 487512594475156591L;
    
    private final MatchMode matchMode;

    protected LikeExpression(String hqlName, String paramKey, String operation, MatchMode matchMode, Class paramClass) {
        super(hqlName, paramKey, operation, paramClass);
        this.matchMode = matchMode;
    }

    protected void setParams(QueryBuilder builder, String value, Class clazz) {
        builder.getNamedQueryParams().put(QueryHelper.qualifyHql(hqlName), matchMode.toMatchString(value));
        builder.getValidQueryParams().put(paramKey, value);
    }

}
