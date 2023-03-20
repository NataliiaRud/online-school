SELECT * FROM student ORDER BY last_name;

SELECT l.name, count(a.id) as number_of_additional_materials FROM
lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id
WHERE l.lecture_date < '2023-01-01 00:00:00'
GROUP BY l.id
ORDER BY l.lecture_date;

SELECT b.id, b.course_id, b.name, b.description, b.lecture_date FROM (
  SELECT l.*, count(a.id) as number_of_additional_materials FROM
  lecture l LEFT JOIN additional_material a ON l.id = a.lecture_id
  GROUP BY l.id
  ORDER BY l.lecture_date, number_of_additional_materials DESC) b
LIMIT 1;

SELECT type, count(type) FROM additional_material GROUP BY type;

SELECT * FROM teacher WHERE UPPER(last_name) < 'N';

SELECT s.* FROM student s, student_course sc
WHERE s.id = sc.student_id
GROUP BY s.id
ORDER BY s.last_name;