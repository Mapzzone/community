create table queston
(
    id int auto_increment,
    title varchar(50) null,
    description TEXT null,
    gmt_create BIGINT null,
    gmt_modified BIGINT null,
    creator int null,
    view_count int default 0 null,
    like_count int default 0 null,
    tag VARCHAR(256) null,
    constraint queston_pk
        primary key (id)
);

