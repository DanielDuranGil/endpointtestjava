DROP TABLE IF EXISTS balances;
DROP TABLE IF EXISTS transactions;

CREATE TABLE balances
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    account_iban VARCHAR(50)    NOT NULL UNIQUE,
    total        DECIMAL(50, 2) NOT NULL DEFAULT 0
);

CREATE TABLE transactions
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    reference        VARCHAR(50)    NOT NULL UNIQUE,
    account_iban     VARCHAR(50)    NOT NULL,
    transaction_date TIMESTAMP      NOT NULL,
    amount           DECIMAL(50, 2) NOT NULL,
    fee              DECIMAL(50, 2) NOT NULL,
    description      VARCHAR(250) DEFAULT NULL,
    foreign key (account_iban) references balances (account_iban)
);

