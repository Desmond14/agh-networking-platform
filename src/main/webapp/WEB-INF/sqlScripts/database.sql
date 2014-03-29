DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  USER_ID serial PRIMARY KEY NOT NULL,
  USERNAME VARCHAR(45) NOT NULL UNIQUE,
  PASSWORD VARCHAR(45) NOT NULL,
  ENABLED boolean NOT NULL
);

CREATE TABLE user_roles (
  USER_ROLE_ID serial PRIMARY KEY NOT NULL,
  AUTHORITY VARCHAR(45) NOT NULL,
  USER_ID serial references users(USER_ID)
);

insert into users (username, password, enabled) values ('kkrzys', '1234', true);
insert into user_roles (authority, user_id) values ('ROLE_USER', 1);
