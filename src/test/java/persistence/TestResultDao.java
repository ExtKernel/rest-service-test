package persistence;

import model.TestResult;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(TestResult.class)
public interface TestResultDao {

    @SqlQuery("SELECT * FROM test_results WHERE test_name = ?")
    TestResult findByName(String testName);

    @SqlUpdate("""
        INSERT INTO test_results(test_name, status, execution_time)
        VALUES (:testName, :status, :executionTime)
    """)
    void insert(@BindBean TestResult result);

    @SqlUpdate("""
        UPDATE test_results
        SET   status = :status,
              execution_time = :executionTime
        WHERE test_name = :testName
    """)
    void update(@BindBean TestResult result);
}