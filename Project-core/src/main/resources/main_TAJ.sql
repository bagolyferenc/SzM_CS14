create table TAJ
(
    id         INTEGER not null
        constraint TAJ_pk
            primary key,
    tajszam    text    not null,
    name       text    not null,
    vercsoport text    not null,
    lakcim     text    not null,
    szhely     text    not null,
    anev       text    not null,
    sznap      text    not null
);

create unique index TAJ_id_uindex
    on TAJ (id);

create unique index TAJ_tajszam_uindex
    on TAJ (tajszam);

