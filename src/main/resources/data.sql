INSERT INTO balances (account_iban, total)
VALUES ('ES1111111111111111111111', 20000),
       ('ES2222222222222222222222', 50000),
       ('ES3333333333333333333333', -100);

INSERT INTO transactions (reference, account_iban, transaction_date, amount, fee, description)
VALUES ('111111A', 'ES1111111111111111111111', parsedatetime('20-06-2020 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 550,
        3.18, 'transaction test 1'),
       ('111111B', 'ES1111111111111111111111', parsedatetime('20-06-2020 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 450.5,
        0, 'transaction test 2'),
       ('111111C', 'ES1111111111111111111111', parsedatetime('20-06-2100 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 450.5,
        0, 'transaction test 3'),
       ('222222A', 'ES2222222222222222222222', parsedatetime('20-06-2020 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),
        -10000, 0, 'transaction test 4');
