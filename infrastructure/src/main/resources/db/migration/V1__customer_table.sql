create table customers
(
    id          serial primary key,
    external_id binary(16) not null,
    email       varchar(255),
    index (external_id),
    index (email)
);

create table customer_addresses
(
    id           serial primary key,
    customer_id  binary(16)   not null,
    street       varchar(100) not null,
    city         varchar(30) not null,
    country      varchar(30) not null,
    zip          varchar(10)  not null,
    instructions varchar(255),
    index (customer_id),
    foreign key (customer_id) references customers (external_id)
);
