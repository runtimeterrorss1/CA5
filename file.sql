create database IF NOT EXISTS mlopss ;
USE mlopss;

CREATE TABLE IF NOT EXISTS user1 (
    email VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    PRIMARY KEY (email)
);

INSERT INTO user1 (email, pass) VALUES ('user1@example.com', 'password123');
INSERT INTO user1 (email, pass) VALUES ('user2@example.com', 'securepass456');
INSERT INTO user1 (email, pass) VALUES ('user3@example.com', 'securepass78');
INSERT INTO user1 (email, pass) VALUES ('user4@example.com', 'securepass496');
