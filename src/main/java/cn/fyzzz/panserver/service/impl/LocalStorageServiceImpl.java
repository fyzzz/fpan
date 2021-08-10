package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.config.PanConfig;
import cn.fyzzz.panserver.constant.FileAttributeEnum;
import cn.fyzzz.panserver.exception.ServiceException;
import cn.fyzzz.panserver.model.model.FileInfo;
import cn.fyzzz.panserver.model.vo.FileVo;
import cn.fyzzz.panserver.service.StorageService;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fyzzz
 * 2021/8/6 10:15 上午
 */
@Slf4j
@Service
public class LocalStorageServiceImpl implements StorageService {

    @Autowired
    private PanConfig panConfig;

    @Override
    public List<FileVo> fileList(String path) {
        List<FileVo> fileVoList = new ArrayList<>();

        File file = new File(panConfig.getLocalStorage().getRootPath(), path);

        if (!file.exists()) {
            log.error("文件不存在：{}", file.getAbsoluteFile());
            throw new ServiceException("文件不存在");
        }

        File[] files = file.listFiles();

        if (files == null) {
            return fileVoList;
        }
        for (File f : files) {
            FileVo fileVo = new FileVo();
            fileVo.setType(f.isDirectory() ? FileAttributeEnum.FOLDER : FileAttributeEnum.FILE);
            fileVo.setLastModified(new Date(f.lastModified()));
            fileVo.setSize(f.length());
            fileVo.setName(f.getName());
            fileVo.setPath(path);
            if (f.isFile()) {
                // todo 获取下载url
//                fileVo.setUrl(getDownloadUrl(StringUtils.concatUrl(path, f.getName())));
            }
            fileVoList.add(fileVo);
        }
        return fileVoList;
    }

    @Override
    public void mkdir(String path) {
        File file = new File(panConfig.getLocalStorage().getRootPath(), path);
        if (file.exists()) {
            throw new ServiceException("目录已存在");
        }
        if (!file.mkdirs()) {
            throw new ServiceException("目录创建失败：" + path);
        }
    }

    @Override
    public void move(String srcPath, String targetPath) {
        File src = new File(panConfig.getLocalStorage().getRootPath(), srcPath);
        File target = new File(panConfig.getLocalStorage().getRootPath(), targetPath);
        if(!src.exists()){
            throw new ServiceException("源文件不存在");
        }
        if(!target.exists() || !target.isDirectory()){
            throw new ServiceException("目标目录不存在");
        }
        FileUtil.move(src, target ,true);
    }

    @Override
    public void delete(String path) {
        File file = new File(panConfig.getLocalStorage().getRootPath(), path);
        FileUtil.del(file);
    }

    @Override
    public void upload(String path, MultipartFile uploadFile, Integer fileId) throws IOException {

        String md5Digest = DigestUtils.md5DigestAsHex(uploadFile.getInputStream());
        log.info("文件getName{}", uploadFile.getName());
        log.info("文件getOriName{}", uploadFile.getOriginalFilename());
        log.info("文件md5{}", md5Digest);
        String absolutePath = new File(panConfig.getLocalStorage().getRootPath()).getAbsolutePath();
        uploadFile.transferTo(new File(absolutePath, md5Digest));

    }

    @Override
    public void download(String path, HttpServletResponse response) {
        File file = new File(panConfig.getLocalStorage().getRootPath(), path);
        if(!file.exists()){
            throw new ServiceException("文件不存在");
        }
        if (file.isDirectory()){
            // todo 考虑下载文件夹怎么做
            throw new ServiceException("暂不支持下载文件夹");
        }
        response.reset();
        response.setContentType("application/force-download");
        // 设置文件名
        try {
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件下载失败", e);
        }

        byte[] buffer = new byte[1024];
        try(FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            log.error("文件下载失败",e);
        }
    }

}
