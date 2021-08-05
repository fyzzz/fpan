package cn.fyzzz.panserver.controller;


import cn.fyzzz.panserver.exception.ServiceException;
import cn.fyzzz.panserver.model.DO.FileInfo;
import cn.fyzzz.panserver.model.SysResult;
import cn.fyzzz.panserver.service.FileInfoService;
import cn.fyzzz.panserver.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.UUID;

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
    @Autowired
    private UserInfoService userInfoService;
    @Value("${upload.root.path}")
    private String uploadRootPath;

    @PostMapping("/page")
    public SysResult page(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestBody FileInfo fileInfo
    ){
        // 暂时只查自己的
        fileInfo.setUserId(userInfoService.currentUserId());
        return SysResult.ok(fileInfoService.page(pageNum,pageSize,fileInfo));
    }

    @PostMapping("/save")
    public SysResult save(@RequestBody FileInfo fileInfo){
        fileInfo.setUserId(userInfoService.currentUserId());
        return SysResult.ok(fileInfoService.saveOrUpdate(fileInfo));
    }

    @PostMapping("/upload")
    public SysResult insert(MultipartFile uploadFile, HttpServletRequest request){
        File rootDir = new File(uploadRootPath);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        File uploadDir = new File(rootDir.getAbsolutePath()+File.separator+year+File.separator+month+File.separator+day);
        if(!uploadDir.isDirectory() && !uploadDir.mkdirs()){
            log.error("创建文件夹失败{}",uploadDir.getAbsolutePath());
            throw new ServiceException("创建文件夹失败");
        }
        String uploadFileName = uploadFile.getOriginalFilename();
        if(!StringUtils.hasLength(uploadFileName)){
            return SysResult.error("上传文件名为空。");
        }
        String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        String fileName = fileType.substring(1)+ UUID.randomUUID().toString().replace("-","")+fileType;
        try {
            uploadFile.transferTo(new File(uploadDir,fileName));
        } catch (IOException e) {
            log.error("文件上传失败！",e);
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileUploadName(uploadFileName);
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(uploadDir.getAbsolutePath());
        fileInfoService.save(fileInfo);
        return SysResult.ok(fileInfo.getId());
    }

    @PostMapping("/delete/{id}")
    public SysResult delete(@PathVariable("id") int id){
        fileInfoService.delete(id);
        return SysResult.ok();
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable("id") int id, HttpServletResponse response){
        FileInfo fileInfo = fileInfoService.getById(id);
        if(fileInfo == null){
            return;
        }
        File file = new File(fileInfo.getFilePath(),fileInfo.getFileName());
        if(file.exists() && file.isFile()){
            response.reset();
            response.setContentType("application/force-download");
            // 设置文件名
            try {
                response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileInfo.getFileUploadName(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("文件下载失败",e);
            }

            FileInputStream fis = null;
            BufferedInputStream bis  = null;
            byte[] buffer = new byte[1024];
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                log.error("文件下载失败",e);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        log.error("文件下载失败",e);
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        log.error("文件下载失败",e);
                    }
                }
            }
        }
    }

}
