truncate table users cascade;
truncate table media cascade;

insert into users(id, email, password, time_created)values
(200, 'john@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(201, 'jane@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(202, 'jenny@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(203, 'james@email.com', 'password', '2024-06-04T15:03:54.583845146');

insert into media (id, category, description, url, time_created)values
(100, 'ACTION', 'media 1', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(101, 'COMEDY', 'media 2', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(102, 'ROMANCE', 'media 3', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(103, 'SCI_FI', 'media 4', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(104, 'DRAMA', 'media 5', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146');