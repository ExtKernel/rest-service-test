package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResult {
    private int id;
    private String testName;
    private String status;
    private Long createdAt;
}