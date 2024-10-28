-- Insertar cuentas
INSERT INTO accounts (balance) VALUES (1000.00); -- Cuenta 1
INSERT INTO accounts (balance) VALUES (2000.00); -- Cuenta 2

-- Insertar movimientos para la cuenta 1
INSERT INTO movements (account_id, amount) VALUES (1, 1000.00);

-- Insertar movimientos para la cuenta 2
INSERT INTO movements (account_id, amount) VALUES (2, 2000.00);