ALTER DATABASE bu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- test teacher data
insert into bu_teacher (id, first_name, last_name, patronymic)
values (1, 'Ольга', 'Артеменко', 'Іванівна'),
       (2, 'Тетяна', 'Штерма', 'Василівна'),
       (3, 'Валерій', 'Євдокименко', 'Кирилович');

-- group test data
insert into bu_group (id, curator_id, name)
values (1, 1, 'KM-501'),
       (2, 2, 'ФМ-501'),
       (3, 3, 'АМ-501');

-- student dest data
insert into bu_student (id, first_name, group_id, last_name, patronymic)
values (1, 'Антон', 1, 'Расщектаєв', 'Володимирович'),
       (2, 'Роман', 1, 'Дудник', 'Борисович'),
       (3, 'Іван', 1, 'Павлюк', 'Іванович'),
       (4, 'Іван', 1, 'Головач', 'Павлович'),
       (5, 'Ігор', 1, 'Ящук', 'Миколайович');

-- subject test data
insert into bu_subject (id, name, teacher_id, group_id)
values (1, 'Основи програмування', 1 ,1),
       (2, 'Менеджмент знань', 2 ,1),
       (3, 'Методологія наукових досліждень', 3 ,1),
       (4, 'Педагогіка та методика вищої школи', 2 ,1),
       (5, 'Нечіткі моделі та методи обчислювального інтелекту', 1 ,1);

-- lesson test data
insert into bu_lesson (id, date, subject_id)
values (1, '2023-03-01', 1),
       (2, '2023-03-08', 1),
       (3, '2023-03-15', 1),
       (4, '2023-03-22', 1),
       (5, '2023-03-29', 1),
       (6, '2023-04-04', 1),
       (7, '2023-04-11', 1),
       (8, '2023-04-18', 1),
       (9, '2023-04-25', 1),
       (10, '2023-05-02', 1);
