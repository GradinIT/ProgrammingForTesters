package se.jocke.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jocke.common.aspects.annotation.Logg;
import se.jocke.common.aspects.annotation.TimeAndLogg;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.department.dao.DepartmentDatabaseEntryMapper;
import se.jocke.department.entity.Department;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @Logg
    public List<Department> getDepartments() {
        return DepartmentDatabaseEntryMapper.map(departmentDao.findAll());
    }

    @Logg
    public Department getDepartmentById(Integer departmentId) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(departmentId);
        if (departmentDatabaseEntry.isPresent())
            return DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry.get());
        throw new EntityNotFoundException(departmentId);
    }

    @Logg
    public Department create(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId().getId());
        if(departmentDatabaseEntry.isPresent())
            throw new EntityAlreadyInStorageException(department.getDepartmentId().getId());
        return DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(department)));
    }

    @Logg
    public Department update(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId().getId());
        if(departmentDatabaseEntry.isPresent())
            return DepartmentDatabaseEntryMapper.map(departmentDao.save(DepartmentDatabaseEntryMapper.map(department)));
        throw new EntityNotFoundException(department.getDepartmentId().getId());
    }

    @Logg
    public Department remove(Department department) {
        Optional<DepartmentDatabaseEntry> departmentDatabaseEntry = departmentDao.findById(department.getDepartmentId().getId());
        if(departmentDatabaseEntry.isPresent()) {
            departmentDao.delete(DepartmentDatabaseEntryMapper.map(department));
            return department;
        }
        throw new EntityNotFoundException(department.getDepartmentId().getId());
    }
}
