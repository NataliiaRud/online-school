 CREATE TABLE school (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL,
     PRIMARY KEY(id)
 );

 CREATE TABLE course (
     id INT NOT NULL AUTO_INCREMENT,
     school_id INT NOT NULL,
     name VARCHAR(255) NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (school_id) REFERENCES school(id) ON DELETE CASCADE
 );

 CREATE TABLE teacher (
     id INT NOT NULL AUTO_INCREMENT,
     school_id INT NOT NULL,
     first_name VARCHAR(255) NOT NULL,
     last_name VARCHAR(255) NOT NULL,
     phone VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (school_id) REFERENCES school(id) ON DELETE CASCADE
 );

 CREATE TABLE lecture (
     id INT NOT NULL AUTO_INCREMENT,
     course_id INT NOT NULL,
     teacher_id INT NOT NULL,
     name VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     lecture_date DATETIME NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
     FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE
 );

 CREATE TABLE additional_material (
     id INT NOT NULL AUTO_INCREMENT,
     lecture_id INT NOT NULL,
     name VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     type VARCHAR(255) NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (lecture_id) REFERENCES lecture(id) ON DELETE CASCADE
 );

 CREATE TABLE home_assignment (
     id INT NOT NULL AUTO_INCREMENT,
     lecture_id INT NOT NULL,
     name VARCHAR(255) NOT NULL,
     task TEXT NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (lecture_id) REFERENCES lecture(id) ON DELETE CASCADE
 );

 CREATE TABLE student (
     id INT NOT NULL AUTO_INCREMENT,
     school_id INT NOT NULL,
     first_name VARCHAR(255) NOT NULL,
     last_name VARCHAR(255) NOT NULL,
     phone VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY (school_id) REFERENCES school(id) ON DELETE CASCADE
 );

 CREATE TABLE teacher_course (
     teacher_id INT NOT NULL,
     course_id INT NOT NULL,
     PRIMARY KEY(teacher_id, course_id),
     FOREIGN KEY (teacher_id) REFERENCES teacher(id) ON DELETE CASCADE,
     FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
 );

 CREATE TABLE student_course (
     student_id INT NOT NULL,
     course_id INT NOT NULL,
     PRIMARY KEY(student_id, course_id),
     FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
     FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
 );

 CREATE ALIAS GET_RECORDS_FROM_TABLE AS  $$
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 @CODE
 ResultSet getRecordsFromTable(final Connection conn, final String tableName) throws SQLException
 {
   PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tableName);
   return ps.executeQuery();
 }
 $$;