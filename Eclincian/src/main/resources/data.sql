-- Inserting into the 'users' table
INSERT INTO users(fName, lName, email, address, username, password, role)
VALUES ('John', 'Doe', 'johndoe@gmail.com', '1000 N 4th St,RM 114, Fairfield, IA 52557', 'jdoe', '123j', 'DOCTOR'),
       ('Jane', 'Doe', 'janedoe@gmail.com', '1000 N 4th St,RM 115, Fairfield, IA 52557', 'jadoe', '123a', 'PATIENT'),
       ('Rob', 'Doe', 'Robdoe@gmail.com', '1000 N 4th St,RM 116, Fairfield, IA 52557', 'rdoe', '123r', 'PATIENT'),
       ('Bob', 'Smith', 'bobsmith@gmail.com', '1000 N 4th St,RM 117, Fairfield, IA 52557', 'bsmith', '123b', 'DOCTOR'),
       ('Alice', 'Johnson', 'alicejohnson@gmail.com', '1000 N 4th St,RM 118, Fairfield, IA 52557', 'ajohnson', '123aj',
        'PATIENT'),
       ('Charlie', 'Brown', 'charliebrown@gmail.com', '1000 N 4th St,RM 119, Fairfield, IA 52557', 'cbrown', '123cb',
        'DOCTOR'),
       ('Ayalneh', 'Yilma', 'getaunayaleneh@gmail.com', '1000 N 4th St,RM 120, Fairfield, IA 52557', 'ayilma',
        '12345601', 'ADMIN');

-- Inserting into the 'doctor' table
INSERT INTO doctor(specialization, qualifications, workingHours, user_id)
VALUES ('Cardiology', 'MBBS, MD', '09:00-17:00', 1),
       ('Dermatology', 'MBBS, MD', '10:00-18:00', 4),
       ('Orthopedics', 'MBBS, MS', '08:00-16:00', 6);
INSERT INTO admin(user_id)
VALUES (7);

-- Inserting into the 'medicalRecord' table
INSERT INTO medicalRecord(date, diagnosedCondition, treatmentPlan, doctorNotes)
VALUES ('2022-01-01', 'Hypertension', 'Regular exercise and low salt diet', 'Stable'),
       ('2022-02-02', 'Acne', 'Topical creams and cleansers', 'Improving'),
       ('2022-03-03', 'Back Pain', 'Physical therapy and rest', 'Needs follow up'),
       ('2022-04-04', 'X-ray shows fracture', 'Casting and rest', 'Needs follow up'),
       ('2022-05-05', 'Migraines', 'Pain management and avoiding triggers', 'Stable');

-- Inserting into the 'patient' table
INSERT INTO patient(dateOfBirth, emergencyContact, contactNumber, user_id, medicalrecord_id)
VALUES ('1980-01-01', '112', '1234567890', 2, 1),
       ('1985-02-02', '112', '0987654321', 5, 2),
       ('1990-03-03', '112', '1111111111', 3, 3);

-- Inserting into the 'appointment' table
INSERT INTO appointment(appointmentTime, appointmentDate, doctor_id, patient_id)
VALUES ('10:00:00', '2023-01-01', 1, 2),
       ('10:30:00', '2023-01-02', 1, 3),
       ('11:00:00', '2023-01-03', 4, 5),
       ('11:30:00', '2023-01-04', 6, 3),
       ('12:00:00', '2023-01-05', 6, 2);

-- Inserting into the 'bill' table
INSERT INTO bill(amount, issueDate, patient_id)
VALUES (100.00, '2022-01-02', 2),
       (200.00, '2022-02-03', 3),
       (150.00, '2022-03-04', 5),
       (250.00, '2022-04-05', 3),
       (300.00, '2022-05-06', 2);
