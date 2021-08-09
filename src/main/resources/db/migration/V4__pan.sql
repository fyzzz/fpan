create table if not exists storage_config
(
    id int auto_increment,
    name varchar(255) null comment '名称',
    type varchar(255) null comment '类型',
    root_path varchar(255) null comment '根路径',
    is_enable tinyint default 0 not null comment '是否启用',
    create_time datetime null,
    update_time datetime null,
    constraint storage_config_pk
        primary key (id)
)
    comment '存储介质配置';

create unique index udx_name
    on storage_config (name);

insert into storage_config (`id`, `name`, type, root_path) values (1, '默认存储', 'local', 'upload');

alter table user_info add column storage_id int default 1 comment '存储id' after user_password;

drop table if exists file_info;

create table file_info
(
    id int auto_increment,
    name varchar(255) null comment '文件名',
    path varchar(255) null comment '路径',
    attribute varchar(10) null comment '属性。文件 or 目录',
    type varchar(10) null comment '类型，文件后缀',
    user_id int null comment '用户id',
    storage_id int null comment '存储id',
    digest_code varchar(255) null comment '摘要',
    size int null comment '文件大小',
    file_id varchar(255) null comment '文件id。local就是文件摘要',
    is_delete tinyint null comment '是否删除',
    create_time datetime null,
    update_time datetime null,
    constraint file_info_pk
        primary key (id)
)
    comment '文件信息';
