package com.maksim.spring.mvc_hibernate_aop.dao;

import com.maksim.spring.mvc_hibernate_aop.entity.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> allEmployees =  session.createQuery("from Employee order by id", Employee.class).getResultList();

        return allEmployees;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Employee.class,id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId",id);
        query.executeUpdate();
    }
}
