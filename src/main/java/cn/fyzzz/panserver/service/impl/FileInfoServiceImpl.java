package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.constant.FileAttributeEnum;
import cn.fyzzz.panserver.constant.GlobalConstant;
import cn.fyzzz.panserver.exception.ServiceException;
import cn.fyzzz.panserver.mapper.FileInfoMapper;
import cn.fyzzz.panserver.model.model.FileInfo;
import cn.fyzzz.panserver.model.model.UserInfo;
import cn.fyzzz.panserver.service.FileInfoService;
import cn.fyzzz.panserver.service.StorageContext;
import cn.fyzzz.panserver.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author fyzzz
 * 2019-09-11
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StorageContext storageContext;

    @Override
    public void mkdir(String path) {
        UserInfo currentUser = userInfoService.currentUser();
        LambdaQueryWrapper<FileInfo> lambdaWrapper = lambdaQueryWrapper(currentUser);
        lambdaWrapper.eq(FileInfo::getPath, path);
        if(this.count(lambdaWrapper) > 0){
            throw new ServiceException("文件夹已存在");
        }
        File file = new File(path);
        String parentPath = file.getParent();
        FileInfo saveInfo = new FileInfo();
        saveInfo.setParentId(getParentId(parentPath, currentUser));
        saveInfo.setName(file.getName());
        saveInfo.setPath(path);
        saveInfo.setUserId(currentUser.getId());
        saveInfo.setStorageId(currentUser.getStorageId());
        saveInfo.setAttribute(FileAttributeEnum.FOLDER.getValue());
        baseMapper.insert(saveInfo);
    }

    @Override
    public void move(String srcPath, String targetPath) {
        if (!StringUtils.hasLength(srcPath) || !StringUtils.hasLength(targetPath)) {
            throw new ServiceException("path不能为空");
        }
        // 1、查出当前文件（目录）和所有子文件
        LambdaQueryWrapper<FileInfo> lambdaQuery = lambdaQueryWrapper();
        lambdaQuery.likeRight(FileInfo::getPath, srcPath);
        List<FileInfo> fileInfos = this.list(lambdaQuery);
        // 2、修改path
        for (FileInfo fileInfo : fileInfos) {
            fileInfo.setPath(fileInfo.getPath().replaceFirst(srcPath, targetPath));
        }
        // 3、批量修改
        this.updateBatchById(fileInfos);
    }

    @Override
    public void delete(String path) {
        if (!StringUtils.hasLength(path)) {
            throw new ServiceException("path不能为空");
        }
        LambdaQueryWrapper<FileInfo> lambdaQuery = lambdaQueryWrapper();
        lambdaQuery.likeRight(FileInfo::getPath, path);
        this.remove(lambdaQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer upload(String path, MultipartFile uploadFile) throws IOException {
        UserInfo currentUser = userInfoService.currentUser();
        String filename = uploadFile.getOriginalFilename();
        if(!StringUtils.hasLength(filename)){
            throw new ServerException("文件名为空");
        }
        // todo 校验文件名称
        File file = new File(path, filename);
        String parentPath = file.getParent();
        FileInfo saveInfo = new FileInfo();
        saveInfo.setParentId(getParentId(parentPath, currentUser));
        saveInfo.setName(filename);
        saveInfo.setPath(path);
        saveInfo.setUserId(currentUser.getId());
        saveInfo.setStorageId(currentUser.getStorageId());
        saveInfo.setAttribute(FileAttributeEnum.FILE.getValue());
        if(filename.contains(GlobalConstant.SPOT)){
            saveInfo.setType(filename.substring(filename.indexOf('.') + 1).toLowerCase());
        }
        String digest = DigestUtils.md5DigestAsHex(uploadFile.getInputStream());
        String fileStorageId = getFileStorageId(digest, currentUser.getStorageId());
        if (fileStorageId == null){
            // 没有相同的摘要，再进行存储
            storageContext.getStorageServiceById(currentUser.getStorageId()).upload(path, uploadFile);
        }
        saveInfo.setDigestCode(digest);
        saveInfo.setFileStorageId(fileStorageId);
        baseMapper.insert(saveInfo);
        return saveInfo.getId();
    }

    private LambdaQueryWrapper<FileInfo> lambdaQueryWrapper(){
        return lambdaQueryWrapper(userInfoService.currentUser());
    }

    private LambdaQueryWrapper<FileInfo> lambdaQueryWrapper(UserInfo userInfo){
        LambdaQueryWrapper<FileInfo> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(FileInfo::getUserId, userInfo.getId());
        lambdaQuery.eq(FileInfo::getStorageId, userInfo.getId());
        return lambdaQuery;
    }

    private Integer getParentId(String parentPath, UserInfo currentUser){
        LambdaQueryWrapper<FileInfo> lambdaQueryWrapper = lambdaQueryWrapper(currentUser);
        if(StringUtils.hasLength(parentPath)){
            lambdaQueryWrapper.eq(FileInfo::getPath, parentPath);
            FileInfo parent = baseMapper.selectOne(lambdaQueryWrapper);
            if(parent == null){
                throw new ServiceException("父目录不存在");
            }
            return parent.getParentId();
        }
        return null;
    }

    private String getFileStorageId(String digest, Integer storageId){
        LambdaQueryWrapper<FileInfo> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(FileInfo::getStorageId, storageId);
        lambdaQuery.eq(FileInfo::getDigestCode, digest);
        List<FileInfo> list = this.list(lambdaQuery);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0).getFileStorageId();
    }

}
