INSERT INTO CAMPANHA (ID_CAMPANHA, NOME_CAMPANHA, ID_TIME_CORACAO, DATA_INICIO, DATA_FIM ) 
VALUES (1, 'Campanha - 1', 1, TO_DATE('2017-08-01', 'YYYY-MM-DD'), TO_DATE('2017-08-02', 'YYYY-MM-DD'));

INSERT INTO CAMPANHA (ID_CAMPANHA, NOME_CAMPANHA, ID_TIME_CORACAO, DATA_INICIO, DATA_FIM ) 
VALUES (2, 'Campanha - 2', 2, TO_DATE('2017-07-01', 'YYYY-MM-DD'), TO_DATE('2017-07-20', 'YYYY-MM-DD'));

INSERT INTO CAMPANHA (ID_CAMPANHA, NOME_CAMPANHA, ID_TIME_CORACAO, DATA_INICIO,DATA_FIM ) 
VALUES (3, 'Campanha - 3', 1, TO_DATE('2017-05-01', 'YYYY-MM-DD'), TO_DATE('2017-08-01', 'YYYY-MM-DD'));


INSERT INTO TORCEDORCAMPANHA (ID_TORCEDORCAMPANHA, EMAIL, TIME_CORACAO,ID_CAMPANHA ) 
VALUES (1, 'camapanha@campanha.com.br', 'Santos', 2);

INSERT INTO TORCEDORCAMPANHA (ID_TORCEDORCAMPANHA, EMAIL, TIME_CORACAO,ID_CAMPANHA ) 
VALUES (2, 'camapanha@campanha.com.br', 'Sao Paulo', 1);