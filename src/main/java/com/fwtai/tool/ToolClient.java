package com.fwtai.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fwtai.bean.PageFormData;
import com.fwtai.config.ConfigFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.support.RequestContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 客户端请求|服务器端响应工具类
 */
public final class ToolClient implements Serializable{

    private static final long serialVersionUID = 1L;

    protected static Log logger = LogFactory.getLog(ToolClient.class);

    /**
     * 生成简单类型json字符串,仅用于查询返回,客户端只需判断code是否为200才操作,仅用于查询操作,除了list集合之外都可以用data.map获取数据,list的是data.listData
     */
    public final static String queryJson(final Object object){
        final JSONObject json = new JSONObject();
        if(ToolString.isBlank(object)){
            return queryEmpty();
        }
        if(object instanceof ArrayList<?>){
            final ArrayList<?> list = (ArrayList<?>) object;
            if(list == null || list.size() <= 0){
                return queryEmpty();
            }else{
                if(ToolString.isBlank(list.get(0))){
                    return queryEmpty();
                }else{
                    json.put(ConfigFile.code,ConfigFile.code200);
                    json.put(ConfigFile.msg,ConfigFile.msg200);
                    json.put(ConfigFile.listData,object);
                    final String jsonObj = json.toJSONString();
                    final JSONObject j = JSONObject.parseObject(jsonObj);
                    final String listData = j.getString(ConfigFile.listData);
                    if(listData.equals("[{}]")){
                        return queryEmpty();
                    }
                    return jsonObj;
                }
            }
        }
        if(object instanceof HashMap<?,?>){
            final HashMap<?,?> hashMap = (HashMap<?,?>) object;
            if(hashMap == null || hashMap.size() <= 0){
                return queryEmpty();
            }else{
                json.put(ConfigFile.code,ConfigFile.code200);
                json.put(ConfigFile.msg,ConfigFile.msg200);
                json.put(ConfigFile.map,object);
                return json.toJSONString();
            }
        }
        if(object instanceof List<?>){
            final List<?> list = (List<?>) object;
            if(list == null || list.size() <= 0){
                return queryEmpty();
            }else{
                if(ToolString.isBlank(list.get(0))){
                    return queryEmpty();
                }else{
                    json.put(ConfigFile.code,ConfigFile.code200);
                    json.put(ConfigFile.msg,ConfigFile.msg200);
                    json.put(ConfigFile.listData,object);
                    final String jsonObj = json.toJSONString();
                    final JSONObject j = JSONObject.parseObject(jsonObj);
                    final String listData = j.getString(ConfigFile.listData);
                    if(listData.equals("[{}]")){
                        return queryEmpty();
                    }
                    return jsonObj;
                }
            }
        }
        if(object instanceof Map<?,?>){
            final Map<?,?> map = (Map<?,?>) object;
            if(map == null || map.size() <= 0){
                queryEmpty();
            }else{
                json.put(ConfigFile.code,ConfigFile.code200);
                json.put(ConfigFile.msg,ConfigFile.msg200);
                json.put(ConfigFile.map,object);
                return json.toJSONString();
            }
        }
        if(object instanceof PageFormData){
            final PageFormData pageFormData = (PageFormData) object;
            if(pageFormData == null || pageFormData.size() <= 0){
                return queryEmpty();
            }else{
                json.put(ConfigFile.code,ConfigFile.code200);
                json.put(ConfigFile.msg,ConfigFile.msg200);
                json.put(ConfigFile.map,object);
                return json.toJSONString();
            }
        }
        if(String.valueOf(object).toLowerCase().equals("null") || String.valueOf(object).replaceAll("\\s*","").length() == 0){
            return queryEmpty();
        }else{
            json.put(ConfigFile.code,ConfigFile.code200);
            json.put(ConfigFile.msg,ConfigFile.msg200);
            json.put(ConfigFile.obj,object);
            final String jsonObj = json.toJSONString();
            final JSONObject j = JSONObject.parseObject(jsonObj);
            final String obj = j.getString(ConfigFile.obj);
            if(obj.equals("{}")){
                return queryEmpty();
            }
            return jsonObj;
        }
    }

    /**
     * 查询时得到的数据为空返回的json字符串
     */
    private static final String queryEmpty(){
        final JSONObject json = new JSONObject();
        json.put(ConfigFile.code,ConfigFile.code201);
        json.put(ConfigFile.msg,ConfigFile.msg201);
        return json.toJSONString();
    }

    /**
     * 生成json字符串对象,直接采用JSONObject封装,执行效率会更高;适用于为增、删、改操作时,判断当前的rows是否大于0来确定是否操作成功,一般在service调用,偷懒的人可以使用本方法
     * @param rows 执行后受影响的行数
     */
    public static final String executeRows(final int rows){
        final JSONObject json = new JSONObject();
        if(rows > 0){
            json.put(ConfigFile.code,ConfigFile.code200);
            json.put(ConfigFile.msg,ConfigFile.msg200);
            json.put(ConfigFile.obj,rows);
            return json.toJSONString();
        }else{
            json.put(ConfigFile.code,ConfigFile.code199);
            json.put(ConfigFile.msg,ConfigFile.msg199);
            json.put(ConfigFile.obj,rows);
            return json.toJSONString();
        }
    }

    /**
     * 生成自定义的json对象,直接采用JSONObject封装,执行效率会更高;适用于为增、删、改操作,一般在service调用
     * @param rows 执行后受影响的行数
     * @param success 执行成功的提示消息
     * @param failure 执行失败的提示消息
     */
    public static final String executeRows(final int rows,final String success,final String failure){
        final JSONObject json = new JSONObject();
        if(rows > 0){
            json.put(ConfigFile.code,ConfigFile.code200);
            json.put(ConfigFile.msg,success);
            json.put(ConfigFile.obj,rows);
            return json.toJSONString();
        }else{
            json.put(ConfigFile.code,ConfigFile.code199);
            json.put(ConfigFile.msg,failure);
            json.put(ConfigFile.obj,rows);
            return json.toJSONString();
        }
    }

    /**
     * 生成json格式字符串,code和msg的key是固定的,直接采用JSONObject封装,执行效率会更高,用于增、删、改、查操作,一般在service层调用
     * @param code 相关参数协议
     */
    public static final String createJson(final int code,final String msg){
        final JSONObject json = new JSONObject();
        json.put(ConfigFile.code,code);
        json.put(ConfigFile.msg,msg);
        return json.toJSONString();
    }

    /**
     * 用于生成出现异常信息时的json固定code:204字符串提示,返回给controller层调用,一般在service层调用
     */
    public final static String exceptionJson(){
        final JSONObject json = new JSONObject();
        json.put(ConfigFile.code,ConfigFile.code204);
        json.put(ConfigFile.msg,ConfigFile.msg204);
        return json.toJSONString();
    }

    /**
     * 用于生成出现异常信息时的json固定code:204字符串提示,返回给controller层调用,一般在service层调用
     * @param msg 自定义提示的异常信息
     */
    public final static String exceptionJson(final String msg){
        final JSONObject json = new JSONObject();
        json.put(ConfigFile.code,ConfigFile.code204);
        json.put(ConfigFile.msg,msg);
        return json.toJSONString();
    }

    /**
     * 返回给客户端系统出现异常的提示信息,已返回给客户端,只能在controller层调用,用于增、删、改、查操作的异常返回给客户端
     */
    public final static void responseException(final HttpServletResponse response){
        responseJson(exceptionJson(),response);
        return;
    }

    /**
     * 返回给客户端系统出现异常的提示信息,已返回给客户端,只能在controller层调用,用于增、删、改、查操作的异常返回给客户端
     * @param msg 自定义提示的异常信息
     */
    public final static void responseException(final HttpServletResponse response,final String msg){
        responseJson(exceptionJson(msg),response);
        return;
    }

    /**
     * 通用的响应json返回json对象,只能在是controller层调用
     * @param jsonObject,可以是Bean对象,map;HashMap;List
     * @param response
     */
    public final static void responseJson(final Object jsonObject,final HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control","no-cache");
        PrintWriter writer = null;
        try{
            writer = response.getWriter();
            if(jsonObject instanceof String){
                writer.write(JSON.parse(jsonObject.toString()).toString());
                writer.flush();
                writer.close();
                return;
            }else{
                writer.write(JSONArray.toJSONString(jsonObject));
                writer.flush();
                writer.close();
                return;
            }
        }catch(IOException e){
            e.printStackTrace();
            logger.error("类ToolClient的方法responseJson出现异常",e);
        }finally{
            if(!ToolString.isBlank(writer)){
                writer.close();
            }
        }
    }

    /**
     * 获取项目物理根路径
     */
    public final static String getWebRoot(){
        return RequestContext.class.getResource("/../../").getPath();
    }

    /**
     * 获取项目所在的物理路径,推荐使用
     * @param request
     */
    public final static String getWebRoot(final HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath(File.separator);
    }

    /**
     * 获取表单的请求参数,不含文件域
     * @param request
     */
    public final static PageFormData getFormData(final HttpServletRequest request){
        final PageFormData params = new PageFormData();
        final Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            final String key = paramNames.nextElement();
            final String[] values = request.getParameterValues(key);
            String value = "";
            if(values == null){
                value = "";
            }else{
                for(int i = 0; i < values.length; i++){
                    value = values[i] + ",";
                }
                value = value.substring(0,value.length() - 1);
            }
            params.put(key,value);
        }
        return params;
    }
}