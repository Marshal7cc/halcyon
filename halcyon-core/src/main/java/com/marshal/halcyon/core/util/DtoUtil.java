package com.marshal.halcyon.core.util;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @auth: Marshal
 * @date: 2019/3/9
 * @desc:
 */
public class DtoUtil {

    /**
     * 主键是否为空
     *
     * @param record
     * @return
     */
    public static boolean isPrimaryKeyNull(Object record) {
        Class clazz = record.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                try {
                    Method method = clazz.getMethod("get" + StringUtils.capitalize(field.getName()));
                    if (method.invoke(record) == null) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 新建记录时更新创建时间
     *
     * @param record
     * @return
     */
    public static Object setCreateAttribute(Object record) {
        Class clazz = record.getClass();
        try {
            Method setCreateTimeMethod = clazz.getMethod("setCreateTime", Date.class);
            Method setUpdateTimeMethod = clazz.getMethod("setUpdateTime", Date.class);
            setCreateTimeMethod.invoke(record, new Date());
            setUpdateTimeMethod.invoke(record, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

    /**
     * 更新记录时间
     *
     * @param record
     * @return
     */
    public static Object setUpdateAttribute(Object record) {
        Class clazz = record.getClass();
        try {
            Method setCreateTimeMethod = clazz.getMethod("setUpdateTime", Date.class);
            setCreateTimeMethod.invoke(record, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }
}
