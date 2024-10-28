-- Crear tabla de cuentas
CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          balance DECIMAL(19, 2) NOT NULL
);

-- Crear tabla de movimientos
CREATE TABLE movements (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           account_id BIGINT NOT NULL,
                           amount DECIMAL(19, 2) NOT NULL,
                           FOREIGN KEY (account_id) REFERENCES accounts(id)
);



