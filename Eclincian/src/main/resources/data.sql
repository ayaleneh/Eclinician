
INSERT INTO role (role)
VALUES ('DOCTOR'); --ROLE NUMBER 1--
INSERT INTO role (role)
VALUES ('PATIENT');--ROLE NUMBER 2--
INSERT INTO role (role)
VALUES ('ADMIN');--ROLE NUMBER 3--


-- Inserting into the 'users' table
INSERT INTO users(fName, lName, email, address, username, password)
VALUES ('John', 'Doe', 'johndoe@gmail.com', '1000 N 4th St,RM 114, Fairfield, IA 52557', 'jdoe', '$2a$12$FoR9FOLfo39dzMsu2uX/c.08fk.ukINVE5u9U5e5tmoBif4SdUWsy'),    --jdoe123
       ('Jane', 'Kerby', 'janedoe@gmail.com', '1000 N 4th St,RM 115, Fairfield, IA 52557', 'jadoe', '$2a$12$TWPkPWXmytGHKQaSbEjpwuMpqOqmH1wKytM9Awy9J0c.o3etaGs9q'),  --jkerby123
       ('Rob', 'Doe', 'Robdoe@gmail.com', '1000 N 4th St,RM 116, Fairfield, IA 52557', 'rdoe', '$2a$12$WedBAhQbMFGB2PQNvNVb6urwdMvWMu7F4eGgdK4Wf5F7QS.QIeBpy'),--rdoe123
       ('Bob', 'Smith', 'bobsmith@gmail.com', '1000 N 4th St,RM 117, Fairfield, IA 52557', 'bsmith', '$2a$12$RuiVe9yWs9fbw2KNsixRdekxNoV8H5QbyWYnu.zCiGk6XCJRLiz5G'),--bsmith123
       ('Alice', 'Johnson', 'alicejohnson@gmail.com', '1000 N 4th St,RM 118, Fairfield, IA 52557', 'ajohnson', '$2a$12$QW5NT./i/NT4uoWEpGeFjOcxlU.O.9IlHvGKS34jaYJJfcpz45dNO'),--ajohnson123
       ('Charlie', 'Brown', 'charliebrown@gmail.com', '1000 N 4th St,RM 119, Fairfield, IA 52557', 'cbrown', '$2a$12$RMnHRcK.AUeqQskaJOl2Q.eJTx6JkgKlnzZMWwiOJWH0yox8YeIWO'),--cbrown123
       ('Ayalneh', 'Yilma', 'getaunayaleneh@gmail.com', '1000 N 4th St,RM 120, Fairfield, IA 52557', 'ayilma',
        '$2a$12$1vf7EVdmlxp./OILXiGZE.DR6BRtNjJXIlocLHhEHaYqMzyJMNkjy');--12345601


INSERT INTO users_role (user_id, role_id)
VALUES (1, 1); --1 is doctor
INSERT INTO users_role (user_id, role_id)
VALUES (4, 1);
INSERT INTO users_role (user_id, role_id)
VALUES (6, 1);
INSERT INTO users_role (user_id, role_id)
VALUES (2, 2); --2 is patient
INSERT INTO users_role (user_id, role_id)
VALUES (5, 2);
INSERT INTO users_role (user_id, role_id)
VALUES (3, 2);
INSERT INTO users_role (user_id, role_id)
VALUES (7, 3); --3 is admin

-- Inserting into the 'doctor' table
INSERT INTO doctor(user_id, specialization, qualifications, workingHours, PhoneNumber)
VALUES (1, 'Cardiology', 'MBBS, MD', '09:00-17:00', '2404632079'),
       (4, 'Dermatology', 'MBBS, MD', '10:00-18:00', '2414642080'),
       (6, 'Orthopedics', 'MBBS, MS', '08:00-16:00', '2424652081');

INSERT INTO admin(user_id)
VALUES (7);

-- Inserting into the 'medicalRecord' table
INSERT INTO medicalRecord(date, diagnosedCondition, treatmentPlan, doctorNotes, doctorid)
VALUES ('2022-01-01', 'Hypertension', 'Regular exercise and low salt diet', 'Stable', 1),
       ('2022-02-02', 'Acne', 'Topical creams and cleansers', 'Improving', 4),
       ('2022-03-03', 'Back Pain', 'Physical therapy and rest', 'Needs follow up', 6),
       ('2022-04-04', 'X-ray shows fracture', 'Casting and rest', 'Needs follow up', 4),
       ('2022-05-05', 'Migraines', 'Pain management and avoiding triggers', 'Stable', 6);

-- Inserting into the 'patient' table
INSERT INTO patient(user_id, dateOfBirth, emergencyContact, contactNumber, medicalrecord_id)
VALUES (2, '1980-01-01', '112', '1234567890', 1),
       (5, '1985-02-02', '112', '0987654321', 2),
       (3, '1990-03-03', '112', '1111111111', 3);


-- Creating and inserting into the 'doctor_patient' table
INSERT INTO doctor_patient(doctor_id, patient_id)
VALUES (1, 2),
       (1, 3),
       (4, 5),
       (6, 3),
       (6, 2);

-- Inserting into the 'appointment' table
INSERT INTO appointment(appointmentTime, appointmentDate,appointmentLocation, doctor_id, patient_id)
VALUES ('10:00:00', '2023-01-01','123 South Ave', 1, 2),
       ('10:30:00', '2023-01-02','123 South Ave', 1, 3),
       ('11:00:00', '2023-01-03','123 South Ave', 4, 5),
       ('11:30:00', '2023-01-04','123 South Ave', 6, 3),
       ('12:00:00', '2023-01-05','123 South Ave', 6, 2);

-- Inserting into the 'bill' table
INSERT INTO bill(amount, issueDate, patient_id)
VALUES (100.00, '2022-01-02', 2),
       (200.00, '2022-02-03', 3),
       (150.00, '2022-03-04', 5),
       (250.00, '2022-04-05', 3),
       (300.00, '2022-05-06', 2);
