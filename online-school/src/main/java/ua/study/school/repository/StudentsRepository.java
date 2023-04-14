package ua.study.school.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Student;

import java.util.List;

@Repository
public class StudentsRepository implements BaseRepository<Student> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer getSize() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT count(*) FROM Student", Long.class);
            return query.list().get(0).intValue();
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Student getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Student student) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void add(Integer id, Student student) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Student getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", id);

            List<Student> students = query.list();

            return students.isEmpty() ? null : students.get(0);
        }
    }

    @Override
    public List<Student> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createNativeQuery("{call get_records_from_table('student')}", Student.class);
            return query.list();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("delete from Student where id= :id");
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }
}