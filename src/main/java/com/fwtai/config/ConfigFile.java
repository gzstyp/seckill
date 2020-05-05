package com.fwtai.config;

import java.io.Serializable;

/**
 * 全局统一的配置变量及标识码-客户端和服务器端
 */
public final class ConfigFile implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 自定义code及msg
     */
    public final static int code198 = 198;

    /**
     * 操作失败
     */
    public final static int code199 = 199;

    /**
     * 操作失败
     */
    public final static String msg199 = "操作失败";

    /**
     * 操作成功
     */
    public final static int code200 = 200;

    /**
     * 操作成功
     */
    public final static String msg200 = "操作成功";

    /**
     * 暂无数据
     */
    public final static int code201 = 201;

    /**
     * 暂无数据
     */
    public final static String msg201 = "暂无数据";

    /**
     * 请求参数不完整
     */
    public final static int code202 = 202;

    /**
     * 请求参数不完整
     */
    public final static String msg202 = "请求参数不完整";

    /**
     * 密钥验证失败
     */
    public final static int code203 = 203;

    /**
     * 密钥验证失败
     */
    public final static String msg203 = "密钥验证失败";

    /**
     * 系统出现异常
     */
    public final static int code204 = 204;

    /**
     * 系统出现异常
     */
    public final static String msg204 = "系统出现异常";

    /**
     * 未登录或登录超时
     */
    public final static int code205 = 205;

    /**
     * 未登录或登录超时
     */
    public final static String msg205 = "未登录或登录超时!";

    /**
     * 账号或密码不正确
     */
    public final static int code206 = 206;

    /**
     * 账号或密码不正确
     */
    public final static String msg206 = "账号或密码不正确";

    /**
     * 分页的区间标识符
     */
    public final static String section = "section";

    /**
     * Oracle数据库分页的<=限制
     */
    public final static String KEYROWS = "KEYROWS";

    /**
     * Oracle数据库分页的>=限制
     */
    public final static String KEYRN = "KEYRN";

    /**
     * 自定义全局变量登录人的系统id主键编号标识
     */
    public final static String LOGIN_KEY = "login_key";

    /**
     * 未登录或登录超时跳转到的jsp页面
     */
    public final static String TIMEOUT = "timeout";

    /**
     * 统一全局的total总条数|总记录数json关键字key响应给客户端
     */
    public final static String total = "total";

    /**
     * 统一全局的totalPage总页数json关键字key响应给客户端
     */
    public final static String totalPage = "totalPage";

    /**
     * 统一全局的Easyui里的datagrid数据返回json关键字key响应给客户端
     */
    public final static String rows = "rows";

    /**
     * 统一全局的code的状态码json关键字key关键字响应给客户端
     */
    public final static String code = "code";

    /**
     * 统一全局的msg提示消息json关键字key响应给客户端
     */
    public final static String msg = "msg";

    /**
     * 统一全局的map数据json关键字key响应给客户端
     */
    public final static String map = "map";

    /**
     * 统一全局的obj数据json关键字key响应给客户端
     */
    public final static String obj = "obj";

    /**
     * 统一全局的pageSize每页大小json关键字key响应给客户端或作为Mybatis的分页参数
     */
    public final static String pageSize = "pageSize";

    /**
     * 统一全局的current当前页json关键字key响应给客户端
     */
    public final static String current = "current";

    /**
     * 统一全局的listData数据集合(含分页的数据)json关键字key响应给客户端
     */
    public final static String listData = "listData";

    /**
     * 分页的默认大小
     */
    public final static int size_default = 50;

    /**
     * 拦截器表达式,配置不拦截的url资源,以括号(xxx)什么开头的都不拦截;即不对匹配该值的访问路径拦截（正则）
     */
    public final static String expression = ".*/((app)|(weChat)|(api)).*";
}