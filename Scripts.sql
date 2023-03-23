CREATE TABLE profile_tab
(
    id       serial,
    username varchar(100) UNIQUE,
    email    varchar(100) UNIQUE,
    password varchar(255) NOT NULL,
    balance  float4 DEFAULT 0.0,

    PRIMARY KEY (id)
);

CREATE TABLE role_tab
(
    id    serial,
    title varchar(50) UNIQUE,

    PRIMARY KEY (id)
);

INSERT INTO role_tab
VALUES (1, 'ROLE_USER');
INSERT INTO role_tab
VALUES (2, 'ROLE_ADMIN');

CREATE TABLE users_roles
(
    users_id bigint,
    roles_id bigint,

    FOREIGN KEY (users_id) REFERENCES profile_tab (id),
    FOREIGN KEY (roles_id) REFERENCES role_tab (id)
);

CREATE TABLE organization_tab
(
    id          serial,
    id_profile  bigint       NOT NULL,
    title       varchar(100) NOT NULL,
    description varchar(255) NOT NULL,
    enabled     boolean DEFAULT FALSE,

    UNIQUE (title),
    PRIMARY KEY (id)
);

CREATE TABLE discount_tab
(
    id       serial,
    amount   int  NOT NULL,
    duration date NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE product_tab
(
    id              serial,
    title           varchar(50)  NOT NULL,
    description     varchar(255) NOT NULL,
    price           float8       NOT NULL,
    count           int8         NOT NULL,
    score           float4,
    id_profile      bigint       NOT NULL,
    organization_id bigint       NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE key_words_tab
(
    id         serial,
    id_product bigint      NOT NULL,
    key_word   varchar(50) NOT NULL,

    FOREIGN KEY (id_product) REFERENCES product_tab (id)
);

CREATE TABLE feedback_tab
(
    id          serial,
    id_profile  bigint NOT NULL,
    id_product  bigint NOT NULL,
    description varchar(255),
    score       int2   NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (id_profile) REFERENCES profile_tab (id),
    FOREIGN KEY (id_product) REFERENCES product_tab (id)
);

CREATE TABLE discounts_products
(
    id_discount bigint,
    id_product  bigint,

    FOREIGN KEY (id_product) REFERENCES product_tab (id),
    FOREIGN KEY (id_discount) REFERENCES discount_tab (id)
);

CREATE TABLE purchase_story_tab
(
    id                 serial,
    profile_id         bigint                   NOT NULL,
    organization_title varchar(100)             NOT NULL,
    product_title      varchar(50)              NOT NULL,
    amount             float8                   NOT NULL,
    count              int8                     NOT NULL,
    date_of_purchase   timestamp with time zone NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (profile_id) REFERENCES profile_tab (id) ON DELETE NO ACTION
);

CREATE TABLE notification_tab
(
    id         serial,
    header     varchar(50)              NOT NULL,
    content    varchar(255)             NOT NULL,
    notif_date timestamp with time zone NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE notifications_profiles
(
    profile_id bigint NOT NULL,
    notif_id   bigint NOT NULL,

    FOREIGN KEY (profile_id) REFERENCES profile_tab (id),
    FOREIGN KEY (notif_id) REFERENCES notification_tab (id)
)


