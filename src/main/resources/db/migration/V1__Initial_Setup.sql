-- CREATE SEQUENCE customer_id_sequence;

-- CREATE TABLE customer(
--     id BIGSERIAL DEFAULT nextval('customer_id_sequence') PRIMARY KEY ,
--     name TEXT NOT NULL ,
--     email TEXT NOT NULL ,
--     age INT NOT NULL
-- );


-- Since we are using BIGSERIAL we dont need to create sequence and use nextval()
CREATE TABLE customer(
                         id BIGSERIAL PRIMARY KEY ,
                         name TEXT NOT NULL ,
                         email TEXT NOT NULL ,
                         age INT NOT NULL
);