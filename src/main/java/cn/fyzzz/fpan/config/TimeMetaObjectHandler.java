package cn.fyzzz.fpan.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author fyzzz
 * 2021/8/26 10:07 上午
 */
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    /**
     * 严格模式填充策略,默认有值不覆盖,如果提供的值为null也不填充
     * 重写：有值也覆盖，如果提供的值为null则不填充
     * @param metaObject metaObject meta object parameter
     * @param fieldName  java bean property name
     * @param fieldVal   java bean property value of Supplier
     * @return this
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}
