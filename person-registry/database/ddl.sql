USE personregistrydb;

-- persons table
CREATE TABLE persons (
    id BIGINT IDENTITY PRIMARY KEY,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL
);

-- addresses table
CREATE TABLE addresses (
    id BIGINT IDENTITY PRIMARY KEY,
    person_id BIGINT NOT NULL,
    type NVARCHAR(20) CHECK (type IN ('PERMANENT', 'TEMPORARY')),
    street NVARCHAR(255),
    city NVARCHAR(100),
    postal_code NVARCHAR(20),
    FOREIGN KEY (person_id) REFERENCES persons(id),
    CONSTRAINT uc_person_type UNIQUE (person_id, type)
);

-- contact_details table
CREATE TABLE contact_details (
    id BIGINT IDENTITY PRIMARY KEY,
    address_id BIGINT NOT NULL,
    type NVARCHAR(20), -- PHONE, EMAIL, stb.
    value NVARCHAR(255),
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);
