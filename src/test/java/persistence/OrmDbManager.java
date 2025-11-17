package persistence;

import model.TestResult;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDateTime;

public class OrmDbManager {
    private static final Jdbi jdbi = Jdbi.create("jdbc:sqlite:src/test/resources/test.db")
            .installPlugin(new SqlObjectPlugin());

    static {
        // Create test_results table, if it does not exist
        jdbi.useHandle(handle -> {
            handle.execute("""
                CREATE TABLE IF NOT EXISTS test_results (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  test_name TEXT NOT NULL,
                  status TEXT NOT NULL,
                  execution_time TEXT NOT NULL
                )
            """);
        });
    }

    public static void saveResult(String testName, String status) {
        jdbi.useExtension(TestResultDao.class, dao -> {

            TestResult existing = dao.findByName(testName);

            TestResult result = new TestResult();
            result.setTestName(testName);
            result.setStatus(status);
            result.setExecutionTime(LocalDateTime.now());

            if (existing == null) {
                dao.insert(result);
            } else {
                dao.update(result);
            }
        });
    }
}