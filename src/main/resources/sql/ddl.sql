create table if not exists t_incident (
    id bigint primary key auto_increment comment '事件ID',
    name varchar(100) not null comment '事件名称',
    description varchar(500) comment '事件描述',
    create_user varchar(100) not null comment '事件创建人',
    create_date date not null comment '事件创建时间');

create index if not exists idx_name on t_incident(name);
create index if not exists idx_user on t_incident(create_user);