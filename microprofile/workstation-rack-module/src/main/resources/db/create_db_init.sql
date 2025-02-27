create table t_team
(
    id               bigint     not null
        primary key,
    name             varchar(20)                         not null,
    product          varchar(20)                         not null,
    default_location varchar(10),
    modified_at      timestamp default now(),
    created_at       timestamp default now()
);
create sequence if not exists SEQ_TEAM_ID START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

alter table t_team
    owner to postgres;

create table t_rack
(
    id               bigint       not null
        primary key,
    serial_number    varchar(20)                         not null,
    team_id          bigint                                not null
        constraint t_rack_t_team_id_fk
            references t_team,
    default_location varchar(10),
    status           varchar(20)
        constraint t_rack_status_check
            check ((status)::text = ANY
        ((ARRAY ['AVAILABLE'::character varying, 'BOOKED'::character varying, 'UNAVAILABLE'::character varying])::text[])),
    modified_at      timestamp default now(),
    created_at       timestamp default now()
);
create sequence if not exists SEQ_RACK_ID START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

alter table t_rack
    owner to postgres;

create table t_rack_asset
(
    id        bigint not null
        primary key,
    asset_tag varchar(20),
    rack_id   bigint
        references t_rack,
    modified_at timestamp default now(),
    created_at  timestamp default now()
);
create sequence if not exists SEQ_RACK_ASSET_ID START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

alter table t_rack_asset
    owner to postgres;

create table t_team_member
(
    id          bigint not null
        primary key,
    team_id     bigint                                not null
        constraint t_team_member_t_team_id_fk
            references t_team,
    ctw_id      varchar(8)                          not null,
    name        varchar(20)                         not null,
    modified_at timestamp default now(),
    created_at  timestamp default now()
);
create sequence if not exists SEQ_TEAM_MEMBER_ID START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

alter table t_team_member
    owner to postgres;

create table t_booking
(
    id           bigint   not null
        primary key,
    rack_id      bigint                                not null
        references t_rack,
    requester_id bigint                              not null
        references t_team_member,
    book_from    timestamp                           not null,
    book_to      timestamp,
    modified_at  timestamp default now(),
    created_at   timestamp default now()
);
create sequence if not exists SEQ_BOOKING_ID START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

alter table t_booking
    owner to postgres;

