package cn.fyzzz.fpan.config;

import cn.fyzzz.fpan.model.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fyzzz
 * 2020/4/10 16:48
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public SysResult errorHandler(Exception e){
        log.error(e.getMessage(),e);
        return SysResult.error(e.getMessage());
    }

}
