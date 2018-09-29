package com.springboot.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zt
 * @since 2018-09-29
 */
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 功能名称
     */
    private String name;

    /**
     * url地址
例如/admin/delete样式
     */
    private String code;

    /**
     * 0：不可用；1：可用
     */
    private String status;

    /**
     * 说明
     */
    private String descr;

    /**
     * 1：第一级；2：第二级，以此类推
     */
    private Integer level;

    /**
     * 如果是第一级，父类ID为0；
     */
    private Integer parentId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", status=" + status +
        ", descr=" + descr +
        ", level=" + level +
        ", parentId=" + parentId +
        "}";
    }
}
