CREATE TABLE IF NOT EXISTS calculation_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    inputA DOUBLE,
    inputB DOUBLE,
    result DOUBLE,
    calculation_time TIMESTAMP
);
