package cn.fyzzz.fpan.controller;

import cn.fyzzz.fpan.model.SysResult;
import cn.fyzzz.fpan.model.req.StorageReq;
import cn.fyzzz.fpan.service.StorageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件存储接口
 *
 * @author fyzzz
 * 2021/8/6 10:29 上午
 */
@Api(tags = "文件存储接口")
@Slf4j
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

    @PostMapping("/upload")
    public SysResult upload(String path, MultipartFile uploadFile){
        try {
            storageService.upload(path, uploadFile);
        } catch (IOException e) {
            log.error("上传文件失败", e);
            return SysResult.error("上传文件失败");
        }
        return SysResult.ok();
    }

    @GetMapping("/download")
    public void download(String path, HttpServletResponse response){
        storageService.download(path, response);
    }

}
