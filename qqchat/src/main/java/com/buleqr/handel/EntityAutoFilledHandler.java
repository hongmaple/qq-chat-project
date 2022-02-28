package com.buleqr.handel;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * mybatis-plus 自动填充功能
 * @author buleqr
 */
@Slf4j
public class EntityAutoFilledHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("自动填充创建时间和更新时间");
        Date now = new Date();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
        this.setFieldValByName("isDeleted", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("自动填充更新时间");
        Date now = new Date();
        this.setFieldValByName("updateTime", now, metaObject);
    }
}
