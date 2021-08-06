package cn.fyzzz.panserver.controller;

import cn.fyzzz.panserver.model.SysResult;
import cn.fyzzz.panserver.model.req.StorageReq;
import cn.fyzzz.panserver.service.StorageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文件存储接口
 *
 * @author fyzzz
 * 2021/8/6 10:29 上午
 */
@Api(tags = "文件存储接口")
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/fileList")
    public SysResult fileList(String path){
        // todo 想做成多用户，每个用户可以配置多个存储
        return SysResult.ok(storageService.fileList(path));
    }

    @PostMapping("/mkdir")
    public SysResult mkdir(@RequestBody StorageReq storageReq){
        storageService.mkdir(storageReq.getPath());
        return SysResult.ok();
    }

    @PostMapping("/move")
    public SysResult move(@RequestBody StorageReq storageReq){
        storageService.move(storageReq.getSrc(), storageReq.getTarget());
        return SysResult.ok();
    }

    @PostMapping("/delete")
    public SysResult delete(@RequestBody StorageReq storageReq){
        storageService.delete(storageReq.getPath());
        return SysResult.ok();
    }

}
