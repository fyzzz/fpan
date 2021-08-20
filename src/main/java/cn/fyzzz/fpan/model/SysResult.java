package cn.fyzzz.fpan.model;

import cn.fyzzz.fpan.constant.GlobalConstant;
import lombok.Data;

/**
 * @author fyzzz
 * 2019/8/12 14:48
 */
@Data
public class SysResult {

    private Integer status;

    private String msg;

    private Object data;

    private SysResult(){}

    public static SysResult ok(){
        SysResult ok = new SysResult();
        ok.setStatus(GlobalConstant.SUCCESS);
        return ok;
    }

    public static SysResult ok(Object data){
        SysResult ok = ok();
        ok.setData(data);
        return ok;
    }

    public static SysResult error(String msg){
        SysResult error = new SysResult();
        error.setStatus(GlobalConstant.FAILURE);
        error.setMsg(msg);
        return error;
    }

}
