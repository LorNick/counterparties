-- drop table if exists public.counterparty1

create table public.counterparty1
(
    id bigint not null constraint counterparty1_pkey primary key,
    account_number varchar(20) not null,
    bic varchar(9) not null,
    inn varchar(12) not null,
    kpp varchar(9),
    name varchar(20) not null constraint uq_counterparty1_name unique
);