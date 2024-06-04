truncate table users cascade;
truncate table media cascade;

insert into users(id, email, password, time_created)values
(200, 'john@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(201, 'jane@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(202, 'jenny@email.com', 'password', '2024-06-04T15:03:54.583845146'),
(203, 'james@email.com', 'password', '2024-06-04T15:03:54.583845146');

insert into media (id, category, description, url, time_created)values
(100, 0, 'media 1', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(101, 1, 'media 2', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(102, 2, 'media 3', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(103, 3, 'media 4', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146'),
(104, 4, 'media 5', 'http://www.cloudinary.com/media1', '2024-06-04T15:03:54.583845146');