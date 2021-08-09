# 添加文件大小字段
alter table file_info_bak
    add file_size int default 0 null comment '文件大小，字节' after file_upload_name;