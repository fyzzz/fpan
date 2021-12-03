package cn.fyzzz.fpan.model.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 分片上传
 * @author fyzzz
 * 2021/9/13 2:30 下午
 */
@Data
public class MultipartFileReq {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 当前是第几分片
     */
    private int chunk;

    /**
     * 分片大小
     */
    private long size;

    /**
     * 总共多少分片
     * 是否需要？
     */
    private int chunkTotal;

    /**
     * 文件内容
     */
    private MultipartFile file;

}
