package ua.study.school.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.study.school.models.Course;

import java.util.List;

@Repository
public class CourseRepository implements BaseRepository<Course> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer getSize() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT count(*) FROM Course", Long.class);
            return query.list().get(0).intValue();
        }
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public Course getByIndex(Integer indexToGet) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void add(Course course) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(course);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public void add(Integer id, Course course) {
        // index is not applicable for database
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Course getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("from Course where id = :id", Course.class);
            query.setParameter("id", id);

            List<Course> courses = query.list();

            return courses.isEmpty() ? null : courses.get(0);
        }
    }

    @Override
    public List<Course> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createNativeQuery("{call get_records_from_table('course')}", Course.class);
            return query.list();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("delete from Course where id= :id");
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }
}
