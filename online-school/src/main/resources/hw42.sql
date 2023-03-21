                       # 1
                       SELECT l.name, t.last_name FROM lecture l, teacher t
                       WHERE l.teacher_id = t.id
                       ORDER BY l.lecture_date;

                       # 2
                       SELECT t.last_name, t.first_name, COUNT(l.id)
                       FROM teacher t LEFT JOIN lecture l ON t.id = l.teacher_id
                       GROUP BY t.id;

                       # 3
                       SELECT DATE(lecture_date), name FROM lecture WHERE teacher_id = 3 ORDER BY lecture_date;

                       # 4
                       SELECT c.name,
                              (SELECT COUNT(id) FROM lecture WHERE lecture.course_id = c.id) AS lecture_count,
                              (SELECT COUNT(teacher_id) FROM teacher_course WHERE teacher_course.course_id = c.id) AS teacher_count,
                              (SELECT COUNT(student_id) FROM student_course WHERE student_course.course_id = c.id) AS student_count,
                              (SELECT COUNT(id) FROM additional_material a WHERE a.lecture_id IN (SELECT id FROM lecture WHERE course_id = c.id)) AS additional_material_count,
                              (SELECT COUNT(id) FROM home_assignment h WHERE h.lecture_id IN (SELECT id FROM lecture WHERE course_id = c.id)) AS home_assignment_count
                       FROM course c;

                       # 5
                       SELECT
                           MONTHNAME(STR_TO_DATE(CONCAT('2023-', CONCAT(months.m, '-01')), '%Y-%m-%d')),
                           (SELECT COUNT(l.id) FROM lecture l WHERE MONTH(l.lecture_date) = months.m) as c
                       FROM (SELECT 1 AS m UNION SELECT 2 AS m UNION SELECT 3 AS m UNION SELECT 4 AS m UNION SELECT 5 AS m UNION SELECT 6 AS m
                             UNION SELECT 7 AS m UNION SELECT 8 AS m UNION SELECT 9 AS m UNION SELECT 10 AS m
                             UNION SELECT 11 AS m UNION SELECT 12 AS m) months;

                       # 6
                       SELECT name, c FROM
                       (SELECT 'additional material' as name, COUNT(*) as c FROM additional_material
                       UNION
                       SELECT 'home assignment' as name, COUNT(*) as c FROM home_assignment) as u
                       ORDER BY c DESC LIMIT 1