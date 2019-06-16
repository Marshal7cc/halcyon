package com.marshal.halcyon.core.interceptor;

import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.core.util.RequestHelper;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Statement;
import java.util.Properties;

/**
 * @auth: Marshal
 * @date: 2019/6/16
 * @desc: mybatis拦截器
 * 若HttpServletRequest不为空，则添加SessionContext作为参数
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})
})
@Component
public class SessionContextInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
            HttpServletRequest request = RequestHelper.getHttpServletRequest();
            if (request != null) {
                SessionContext sessionContext = RequestHelper.getSessionContext(request);
                boundSql.setAdditionalParameter("session", sessionContext);
            }
        }
        System.out.println("拦截器执行!");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
