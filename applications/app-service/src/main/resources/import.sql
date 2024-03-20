CREATE TABLE users_aut (
    id UUID,
    name VARCHAR(250),
    email VARCHAR(250) PRIMARY KEY,
    password VARCHAR(250),
    created TIMESTAMP,
    modified TIMESTAMP,
    lastLogin TIMESTAMP,
    token VARCHAR(250),
    isActive BOOLEAN
);

CREATE TABLE phones_users (
    user_email VARCHAR(250),
    number VARCHAR(250) PRIMARY KEY,
    cityCode VARCHAR(250),
    countryCode VARCHAR(250),
    FOREIGN KEY (user_email) REFERENCES users_aut(email)
);

INSERT INTO users_aut (id, name, email, password, created, modified, lastLogin, token, isActive) VALUES ('c46897c8-fab4-4220-88a3-4409192032dc', 'Oscar Alexander', 'oscar@salcedo.co', 'Hunter1210', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', 'a7dc1eb1-96f6-42c2-b747-27188703dd09', true);
INSERT INTO phones_users (user_email, number, cityCode, countryCode) VALUES ('oscar@salcedo.co', '1234567', '1', '58');

INSERT INTO users_aut (id, name, email, password, created, modified, lastLogin, token, isActive) VALUES ('6872f548-c4f6-4db0-91d6-0168cb971abb', 'Laura Gonzales', 'laura@gonzales.co', 'MikelG1210', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', '7e0a4500-9ac2-4886-8e78-38d7f61699f7', true);
INSERT INTO phones_users (user_email, number, cityCode, countryCode) VALUES ('laura@gonzales.co', '987654312', '3', '55');

INSERT INTO users_aut (id, name, email, password, created, modified, lastLogin, token, isActive) VALUES ('15317b79-57b8-46e8-83ec-12cf8492adbf', 'Carlos Padilla', 'carlos@padilla.co', 'Heroico2210', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', '2024-03-20T02:57:23.874+00:00', 'de5586b9-4950-47ee-b9f3-186cf217728b', true);
INSERT INTO phones_users (user_email, number, cityCode, countryCode) VALUES ('carlos@padilla.co', '678954311', '4', '45');