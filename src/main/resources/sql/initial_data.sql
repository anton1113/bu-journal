ALTER DATABASE bu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- test teacher data
insert into bu_teacher (id, first_name, last_name, patronymic)
values (1, 'Ольга', 'Артеменко', 'Іванівна');

-- group test data
insert into bu_group (id, curator_id, name)
values (1, 1, 'KM-501');

-- student dest data
insert into bu_student (id, first_name, group_id, last_name, patronymic)
values (1, 'Антон', 1, 'Расщектаєв', 'Володимирович');

-- discipline test data
insert into bu_subject (id, name, teacher_id, group_id)
values (1, 'Основи програмування', 1 ,1);

-- lesson test data
insert into bu_lesson (id, date, discipline_id, group_id)
values (1, '2023-03-01', 1, 1);

-- attendance test data
insert into bu_attendance (id, lesson_id, mark, student_id, was_present)
values (1, 1, 5, 1, true);
