SELECT * FROM ams.employee;

INSERT INTO `ams`.`employee`
(`employee_id`,
`employee_birth_date`,
`employee_department`,
`employee_email`,
`employee_gender`,
`employee_joining_date`,
`employee_name`)
VALUES
	(1, '1997-03-02', '総務部', 'emp0001@email.com', '男', '2024-04-01', '中島治紀'),
    (2, '1990-05-15', '営業部', 'emp0002@email.com', '女', '2023-10-15', '山田太郎'),
    (3,'1985-11-20', '研究開発部', 'emp0003@email.com', '男', '2023-09-01', '佐藤花子'),
    (4, '1988-04-12', '人事部', 'emp0004@email.com', '女', '2023-08-01', '鈴木一郎'),
    (5, '1992-09-25', '営業部', 'emp0005@email.com', '男', '2023-07-01', '田中恵美'),
    (6, '1987-02-18', '研究開発部', 'emp0006@email.com', '女', '2023-06-01', '伊藤健太'),
    (7, '1995-12-08', '人事部', 'emp0007@email.com', '男', '2023-05-01', '木村真理子'),
    (8, '1993-07-29', '営業部', 'emp0008@email.com', '女', '2023-04-01', '山口和也'),
    (9, '1989-10-03', '研究開発部', 'emp0009@email.com', '男', '2023-03-01', '渡辺美佐子'),
    (10, '1991-01-14', '総務部', 'emp0010@email.com', '女', '2023-02-01', '高橋直人'),
    (11, '1986-06-22', '人事部', 'emp0011@email.com', '男', '2023-01-01', '斎藤理沙');
    
DELETE FROM ams.user_role;
DELETE FROM ams.role;
DELETE FROM ams.user;
DELETE FROM ams.employee;

DROP TABLE ams.user_role;
DROP TABLE ams.role;
DROP TABLE ams.user;
DROP TABLE ams.employee;