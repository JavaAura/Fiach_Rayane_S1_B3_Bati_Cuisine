CREATE TYPE Status AS ENUM ('ONGOING', 'DONE', 'CANCELLED');

CREATE TABLE Customer (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR NOT NULL,
                          address VARCHAR,
                          phone VARCHAR,
                          isProfessional BOOLEAN
);

CREATE TABLE Project (
                         id SERIAL PRIMARY KEY,
                         projectName VARCHAR NOT NULL,
                         profitMargin DOUBLE PRECISION,
                         totalCost DOUBLE PRECISION,
                         projectStatus Status,
                         customerId INTEGER REFERENCES Customer(id)
);

CREATE TABLE Quote (
                       id SERIAL PRIMARY KEY,
                       estimatedAmount DOUBLE PRECISION,
                       emissionDate DATE,
                       validityDate DATE,
                       accepted BOOLEAN,
                       projectId INTEGER REFERENCES Project(id)
);

CREATE TABLE Component (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           vatRate DOUBLE PRECISION,
                           projectId INTEGER REFERENCES Project(id)
);

CREATE TABLE Material (
                          unitCost DOUBLE PRECISION,
                          quantity DOUBLE PRECISION,
                          transportCost DOUBLE PRECISION,
                          qualityCoefficient DOUBLE PRECISION
) INHERITS (Component);

CREATE TABLE Labor (
                       hourlyRate DOUBLE PRECISION,
                       workHours DOUBLE PRECISION,
                       workerProductivity DOUBLE PRECISION
) INHERITS (Component);






