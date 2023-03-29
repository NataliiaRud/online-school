INSERT INTO school (id, name) VALUES (1, 'School 1');

INSERT INTO course (school_id, name) VALUES (1, 'Java Basic');
INSERT INTO course (school_id, name) VALUES (1, 'Java Advanced');
INSERT INTO course (school_id, name) VALUES (1, 'Java Pro');
INSERT INTO course (school_id, name) VALUES (1, 'Java 18');

INSERT INTO student (school_id, first_name, last_name, phone, email) VALUES (1, 'John', 'Smith', '11111111111', 'john.smith@testmail.com');
INSERT INTO student (school_id, first_name, last_name, phone, email) VALUES (1, 'John', 'Granny', '11111111112', 'john.granny@testmail.com');
INSERT INTO student (school_id, first_name, last_name, phone, email) VALUES (1, 'John', 'Black', '11111111113', 'john.black@testmail.com');

INSERT INTO teacher (school_id, first_name, last_name, phone, email) VALUES (1, 'David', 'Smith', '11111111114', 'david.smith@testmail.com');
INSERT INTO teacher (school_id, first_name, last_name, phone, email) VALUES (1, 'Ryan', 'Granny', '11111111115', 'ryan.granny@testmail.com');
INSERT INTO teacher (school_id, first_name, last_name, phone, email) VALUES (1, 'Robert', 'Black', '11111111116', 'robert.black@testmail.com');

INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (2, 1);
INSERT INTO student_course (student_id, course_id) VALUES (3, 1);
INSERT INTO student_course (student_id, course_id) VALUES (1, 2);
INSERT INTO student_course (student_id, course_id) VALUES (2, 2);
INSERT INTO student_course (student_id, course_id) VALUES (1, 3);

INSERT INTO teacher_course (teacher_id, course_id) VALUES (1, 1);
INSERT INTO teacher_course (teacher_id, course_id) VALUES (1, 2);
INSERT INTO teacher_course (teacher_id, course_id) VALUES (2, 2);
INSERT INTO teacher_course (teacher_id, course_id) VALUES (1, 3);
INSERT INTO teacher_course (teacher_id, course_id) VALUES (2, 3);
INSERT INTO teacher_course (teacher_id, course_id) VALUES (3, 3);

INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (1, 1, 'Lecture 1-1', 'Description 1-1', '2022-11-15 09:00:00');
INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (1, 1, 'Lecture 1-2', 'Description 1-2', '2022-12-08 10:30:00');

INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (2, 1, 'Lecture 2-3', 'Description 2-3', '2023-01-03 8:45:00');
INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (2, 2, 'Lecture 2-4', 'Description 2-4', '2023-02-08 11:00:00');

INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (3, 1, 'Lecture 3-5', 'Description 3-5', '2023-03-10 13:45:00');
INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (3, 2, 'Lecture 3-6', 'Description 3-6', '2023-04-04 8:45:00');
INSERT INTO lecture (course_id, teacher_id, name, description, lecture_date) VALUES (3, 3, 'Lecture 3-7', 'Description 3-7', '2023-05-02 9:05:00');

INSERT INTO additional_material (lecture_id, name, description, type) VALUES (1, 'Additional materials 1', 'Description 1', 'URL');
INSERT INTO additional_material (lecture_id, name, description, type) VALUES (2, 'Additional materials 2', 'Description 2', 'VIDEO');
INSERT INTO additional_material (lecture_id, name, description, type) VALUES (3, 'Additional materials 3', 'Description 3', 'BOOK');

INSERT INTO home_assignment (lecture_id, name, task) VALUES (1, 'Home assignment 1', 'Task 1');
INSERT INTO home_assignment (lecture_id, name, task) VALUES (2, 'Home assignment 2', 'Task 2');
INSERT INTO home_assignment (lecture_id, name, task) VALUES (3, 'Home assignment 3', 'Task 3');;