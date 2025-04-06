-- DML: Test data
INSERT INTO persons (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO persons (first_name, last_name) VALUES ('Mary', 'Fidge');
INSERT INTO persons (first_name, last_name) VALUES ('Sebastian', 'Caplar');

INSERT INTO addresses (person_id, type, street, city, postal_code) VALUES (1, 'PERMANENT', 'Main St 1', 'New York', '1234');
INSERT INTO addresses (person_id, type, street, city, postal_code) VALUES (2, 'PERMANENT', 'Permi St 45', 'Manhattan', '9876');
INSERT INTO addresses (person_id, type, street, city, postal_code) VALUES (3, 'PERMANENT', 'Burger St 98', 'Chicago', '6886');
INSERT INTO addresses (person_id, type, street, city, postal_code) VALUES (3, 'TEMPORARY', 'Colebro St 67', 'Austin', '3379');

INSERT INTO contact_details (address_id, type, value) VALUES (1, 'EMAIL', 'john.doe@example.com');
INSERT INTO contact_details (address_id, type, value) VALUES (2, 'EMAIL', 'mary.fidge@example.com');
INSERT INTO contact_details (address_id, type, value) VALUES (2, 'PHONE', '009672288845');
INSERT INTO contact_details (address_id, type, value) VALUES (3, 'EMAIL', 'sebastian.caplar@example.com');
INSERT INTO contact_details (address_id, type, value) VALUES (4, 'EMAIL', 'sebastian.caplar2@example.com');
INSERT INTO contact_details (address_id, type, value) VALUES (4, 'PHONE', '009599433561');
