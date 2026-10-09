CREATE DATABASE IF NOT EXISTS task_db;
USE task_db;

CREATE TABLE IF NOT EXISTS tasks (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
    description TEXT,
    priority INT NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Datos de prueba
INSERT INTO tasks (title, description, priority, due_date, status) VALUES
                                                                       ('Migrar servidor', 'Actualizar el servidor de producción a Java 21', 1, '2026-12-31', 'PENDING'),
                                                                       ('Revisar auditoría', 'Comprobar los logs de seguridad del clúster', 2, '2026-11-15', 'IN_PROGRESS');