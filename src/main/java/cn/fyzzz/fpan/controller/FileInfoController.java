package cn.fyzzz.fpan.controller;


import cn.fyzzz.fpan.model.SysResult;
import cn.fyzzz.fpan.model.req.FileInfoReq;
import cn.fyzzz.fpan.service.FileInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <p>
 * 文件信息 前端控制器
 * </p>
 *
 * @author fyzzz
 * 2019-09-11
 */
@Api(tags = "文件接口")
@Slf4j
@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @GetMapping("/list")
    public SysResult list(@RequestParam(required = false, defaultValue = "/") String path){
        return SysResult.ok(fileInfoService.list(path));
    }

    @PostMapping("/upload")
    public SysResult upload(MultipartFile uploadFile, String path) throws IOException {
        return SysResult.ok(fileInfoService.upload(path, uploadFile));
    }

    @PostMapping("/mkdir")
    public SysResult mkdir(@RequestBody FileInfoReq fileInfo){
        fileInfoService.mkdir(fileInfo.getPath());
        return SysResult.ok();
    }

    @PostMapping("/move")
    public SysResult move(@RequestBody FileInfoReq fileInfo){
        fileInfoService.move(fileInfo.getSrcPath(), fileInfo.getTargetPath());
        return SysResult.ok();
    }

    @PostMapping("/delete")
    public SysResult delete(@RequestBody FileInfoReq fileInfo){
        fileInfoService.delete(fileInfo.getPath());
        return SysResult.ok();
    }

}
