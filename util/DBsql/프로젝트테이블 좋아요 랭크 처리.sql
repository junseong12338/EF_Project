ALTER TABLE EF_USER DROP CONSTRAINT SYS_C0050034;
COMMIT;




INSERT INTO EF_LIKE VALUES(1,1);
                    
INSERT INTO EF_PROJECT VALUES(
			SEQ_EF_PROJECT_IDX.NEXTVAL,
			0,
			'더미데이터',
			'1234',
			0,
			0,					
			SYSDATE,
			SYSDATE,
			1000000
		);


COMMIT;

ROLLBACK;

-- 최종본 
SELECT A.*
FROM (SELECT A.*, B.LIKE_COUNT, RANK() OVER (ORDER BY C.DONATION_RATE ASC, A.PROJECT_IDX ASC) AS "RANK_NO" ,C.DONATION_SUM, C.DONATION_RATE
        FROM EF_PROJECT A, (SELECT A.PROJECT_IDX, 
                            COUNT(B.PROJECT_IDX) AS "LIKE_COUNT"
                            FROM EF_PROJECT A
                            LEFT JOIN EF_LIKE B ON A.PROJECT_IDX = B.PROJECT_IDX
                            GROUP BY A.PROJECT_IDX) B,
                           (SELECT B.PROJECT_IDX, DONATION_SUM, 
                            ROUND(B.DONATION_SUM / A.PROJECT_TARGET*100) AS DONATION_RATE
                            FROM EF_PROJECT A,(SELECT A.PROJECT_IDX, 
                                               SUM(B.DONATION_MONEY) AS "DONATION_SUM"
                                               FROM EF_PROJECT A
                                               LEFT JOIN EF_DONATION B ON A.PROJECT_IDX = B.PROJECT_IDX
                                               GROUP BY A.PROJECT_IDX) B
                            WHERE A.PROJECT_IDX = B.PROJECT_IDX)C
        WHERE A.PROJECT_IDX = B.PROJECT_IDX 
        AND A.PROJECT_IDX = C.PROJECT_IDX
        AND A.PROJECT_IDX IN (SELECT PROJECT_IDX
                              FROM EF_CATEGORY
                              WHERE CATEGORY_IDX = 1)
        ) A
WHERE A.RANK_NO BETWEEN 1 AND 10;





INSERT INTO EF_DONATION VALUES(1,35,20000);
