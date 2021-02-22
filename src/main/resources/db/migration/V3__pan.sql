create table if not exists daily_record
(
    id             int auto_increment comment ' 主键 ',
    user_id        int                                       null comment ' 归属人id ',
    record_content varchar(500)                              null comment ' 记录内容 ',
    record_date    date                                      null comment ' 记录时间 ',
    is_delete      int          default 0                    null comment ' 是否删除 ',
    create_time    TIMESTAMP(3) default CURRENT_TIMESTAMP(3) null comment ' 创建时间 ',
    update_time    timestamp(3)                              null comment ' 修改时间 ',
    constraint daily_record_pk
        primary key (id)
)
    comment '日志';
